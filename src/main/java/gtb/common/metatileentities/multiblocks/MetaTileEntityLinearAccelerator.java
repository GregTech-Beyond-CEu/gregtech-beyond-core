package gtb.common.metatileentities.multiblocks;

import static gtb.api.utils.GTBMultiblockDisplayTextUtil.*;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtb.api.capabilities.GTBMultiblockAbilities;
import gtb.api.capabilities.IKevMachine;
import gtb.api.capabilities.KevContainer;
import gtb.api.metatileentity.multiblock.KevGeneratorLogic;
import gtb.api.render.GTBTextures;
import gtb.api.utils.GTBMultiblockDisplayTextUtil;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing2;

public class MetaTileEntityLinearAccelerator extends MultiblockWithDisplayBase implements IControllable, IKevMachine {

    private final KevGeneratorLogic logic;
    public static final int BASE_KEV_PRODUCTION = 3000;
    public static final int BASE_EU_CONSUMPTION = 1024;

    public MetaTileEntityLinearAccelerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.logic = new KevGeneratorLogic(this, BASE_EU_CONSUMPTION, BASE_KEV_PRODUCTION);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        logic.initializeAbilities();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        logic.resetTileAbilities();
    }

    @Override
    protected void updateFormedValid() {
        this.logic.update();
    }

    public IBlockState getCasingState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING2.getState(GTBMultiblockCasing2.CasingType.LINEAR_ACCELERATOR_CASING);
    }

    public IBlockState getGlassState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    public IBlockState getBerylliumDetectorState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING2.getState(GTBMultiblockCasing2.CasingType.BERYLLIUM_DETECTOR_CASING);
    }

    public IBlockState getNeodymiumMagnetState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING2.getState(GTBMultiblockCasing2.CasingType.NEODYMIUM_MAGNET_CASING);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                .aisle("CCCCC", "CCCCC", "SCCCC", "CCCCC", "CCCCC")
                .aisle("GGGGG", "GOOOG", "GOOOG", "GOOOG", "GGGGG").setRepeatable(6)
                .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                .aisle("CCCCC", "CNBNC", "CNBNC", "CNBNC", "CCCCC")
                .aisle("CCCCC", "CBNBC", "CBNBC", "CBNBC", "CCCCC")
                .aisle("CCCCC", "CNBNC", "CNBNC", "CNBNC", "CCCCC")
                .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                .where('S', selfPredicate())
                .where('O', abilities(GTBMultiblockAbilities.KEV_COOLER))
                .where('G', states(getGlassState()))
                .where('B', states(getBerylliumDetectorState()))
                .where('N', states(getNeodymiumMagnetState()))
                .where('C', states(getCasingState()).setMinGlobalLimited(18)
                        .or(autoAbilities()))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return super.autoAbilities()
                .or(abilities(MultiblockAbility.INPUT_ENERGY).setMaxGlobalLimited(2, 2))
                .or(abilities(GTBMultiblockAbilities.KEV_CONTAINER_OUTPUT).setExactLimit(1));
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.isActive(),
                this.isWorkingEnabled());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTBTextures.LINEAR_ACCELERATOR_CASING_OVERLAY;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLinearAccelerator(metaTileEntityId);
    }

    @Override
    public boolean isActive() {
        return super.isActive() && this.logic.isWorking();
    }

    @Override
    protected void addWarningText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed(), false)
                .addLowPowerLine(this.logic.hasNotEnoughEnergy())
                .addMaintenanceProblemLines(getMaintenanceProblems());
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(isWorkingEnabled(), isActive())
                .addEnergyUsageExactLine(this.logic.getEuConsumption())
                .addIdlingLine(isActive())
                .addRunningPerfectlyLine(true)
                .addCustom(addColorNumber(
                        TextFormatting.AQUA,
                        this.logic.getCoolingAmount(),
                        TextFormatting.WHITE,
                        "gtb.multiblock.kev_generator.cooling"))
                .addCustom(GTBMultiblockDisplayTextUtil.addColorNumberCondition(
                        TextFormatting.GREEN,
                        this.logic.getKevContainer().getKev(),
                        TextFormatting.WHITE,
                        "gtb.multiblock.kev_generator.kev_production",
                        isActive()))
                .addCustom(GTBMultiblockDisplayTextUtil.addColorNumberCondition(TextFormatting.GREEN,
                        this.logic.getKevProduction(),
                        TextFormatting.GRAY,
                        "gtb.multiblock.kev_generator.kev_production.expected",
                        !isActive()));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        tooltip.add(I18n.format("gtb.machine.kev_generator.usage"));
        tooltip.add(I18n.format("gregtech.universal.tooltip.uses_per_tick", BASE_EU_CONSUMPTION));
        tooltip.add(I18n.format("gtb.machine.kev_generator.base_kev_production", BASE_KEV_PRODUCTION));
        tooltip.add(I18n.format("gtb.machine.kev_generator.coolers.tooltip"));
        tooltip.add(I18n.format("gtb.machine.kev_generator.output_hatch.tooltip"));
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return super.getCapability(capability, side);
    }

    @Override
    public boolean isWorkingEnabled() {
        return this.logic.isWorkingEnabled();
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        this.logic.setWorkingEnabled(isWorkingAllowed);
    }

    @Override
    public KevContainer getKevContainer() {
        return this.logic.getKevContainer();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        return this.logic.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.logic.readFromNBT(data);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        this.logic.writeInitialSyncData(buf);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.logic.receiveInitialSyncData(buf);
    }

    @Override
    public void receiveCustomData(int dataId, @NotNull PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        this.logic.receiveCustomData(dataId, buf);
    }
}
