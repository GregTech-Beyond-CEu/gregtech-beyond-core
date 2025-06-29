package gtb.common.metatileentities.multiblocks;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.GTUtility;
import gregtech.api.util.RelativeDirection;
import gregtech.api.util.TextComponentUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtb.api.capabilities.GTBMultiblockAbilities;
import gtb.api.capabilities.IKevMachine;
import gtb.api.capabilities.KevContainer;
import gtb.api.metatileentity.multiblock.KevConsumerLogic;
import gtb.api.recipes.GTBRecipeMaps;
import gtb.api.render.GTBTextures;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBBlockWireCoil;
import gtb.common.block.blocks.GTBMultiblockCasing2;
import lombok.Getter;

@Getter
public class MetaTileEntityParticleAccelerator extends RecipeMapMultiblockController implements IKevMachine {

    private KevContainer kevContainer;

    public MetaTileEntityParticleAccelerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.PARTICLE_ACCELERATOR_RECIPES);
        this.recipeMapWorkable = new KevConsumerLogic(this);
    }

    @Override
    protected void initializeAbilities() {
        super.initializeAbilities();
        if (!getAbilities(GTBMultiblockAbilities.KEV_CONTAINER_INPUT).isEmpty()) {
            this.kevContainer = getAbilities(GTBMultiblockAbilities.KEV_CONTAINER_INPUT).get(0);
        }
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle(
                        "~~~~~~~~~~~~~~~",
                        "~~~~~~GGG~~~~~~",
                        "~~~~~~~~~~~~~~~")
                .aisle(
                        "~~~~~~GGG~~~~~~",
                        "~~~~GGCCCGG~~~~",
                        "~~~~~~GSG~~~~~~")
                .aisle(
                        "~~~~GG~~~GG~~~~",
                        "~~~GCCGGGCCG~~~",
                        "~~~~GG~~~GG~~~~")
                .aisle(
                        "~~~G~~~~~~~G~~~",
                        "~~GCGG~~~GGCG~~",
                        "~~~G~~~~~~~G~~~")
                .aisle(
                        "~~G~~~~~~~~~G~~",
                        "~GCG~~~~~~~GCG~",
                        "~~G~~~~~~~~~G~~")
                .aisle(
                        "~~G~~~~~~~~~G~~",
                        "~GCG~~~~~~~GCG~",
                        "~~G~~~~~~~~~G~~")
                .aisle(
                        "~G~~~~~~~~~~~G~",
                        "GCG~~~~~~~~~GCG",
                        "~G~~~~~~~~~~~G~")
                .aisle(
                        "~G~~~~~~~~~~~G~",
                        "GCG~~~~~~~~~GCG",
                        "~G~~~~~~~~~~~G~")
                .aisle(
                        "~G~~~~~~~~~~~G~",
                        "GCG~~~~~~~~~GCG",
                        "~G~~~~~~~~~~~G~")
                .aisle(
                        "~~G~~~~~~~~~G~~",
                        "~GCG~~~~~~~GCG~",
                        "~~G~~~~~~~~~G~~")
                .aisle(
                        "~~G~~~~~~~~~G~~",
                        "~GCG~~~~~~~GCG~",
                        "~~G~~~~~~~~~G~~")
                .aisle(
                        "~~~G~~~~~~~G~~~",
                        "~~GCGG~~~GGCG~~",
                        "~~~G~~~~~~~G~~~")
                .aisle(
                        "~~~~GG~~~GG~~~~",
                        "~~~GCCGGGCCG~~~",
                        "~~~~GG~~~GG~~~~")
                .aisle(
                        "~~~~~~GGG~~~~~~",
                        "~~~~GGCCCGG~~~~",
                        "~~~~~~GGG~~~~~~")
                .aisle(
                        "~~~~~~~~~~~~~~~",
                        "~~~~~~GGG~~~~~~",
                        "~~~~~~~~~~~~~~~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('G',
                        states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING2
                                .getState(GTBMultiblockCasing2.CasingType.PARTICLE_PROTECTION_CASING))
                                        .setMinGlobalLimited(116)
                                        .or(autoAbilities()))
                .where('C', states(GTBMetaBlocks.GTB_BLOCK_WIRE_COIL.getState(GTBBlockWireCoil.GTBCoilType.ADAMANTIUM)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false)
                .or(abilities(GTBMultiblockAbilities.KEV_CONTAINER_INPUT))
                .setExactLimit(1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTBTextures.PARTICLE_PROTECTION_CASING_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(),
                recipeMapWorkable.isActive(), recipeMapWorkable.isWorkingEnabled());
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityParticleAccelerator(metaTileEntityId);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        tooltip.add(I18n.format("gtb.machine.kev_consumer.usage"));
        tooltip.add(I18n.format("gtb.machine.kev_consumer.generator.tooltip"));
        tooltip.add(I18n.format("gtb.machine.kev_consumer.recipe.tooltip"));
        tooltip.add(I18n.format("gtb.machine.kev_consumer.fail_recipe.tooltip", "nuclear waste"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(recipeMapWorkable.isWorkingEnabled(), recipeMapWorkable.isActive())
                .addEnergyUsageLine(recipeMapWorkable.getEnergyContainer())
                .addEnergyTierLine(GTUtility.getTierByVoltage(recipeMapWorkable.getMaxVoltage()))
                .addParallelsLine(recipeMapWorkable.getParallelLimit())
                .addCustom(list -> {
                    if (kevContainer.getKev() > 0) {
                        list.add(TextComponentUtil.translationWithColor(TextFormatting.GREEN,
                                "gtb.multiblock.kev_reception", kevContainer.getKev()));
                    } else {
                        list.add(TextComponentUtil.translationWithColor(TextFormatting.RED,
                                "gtb.multiblock.kev_reception.none"));
                    }
                })
                .addWorkingStatusLine()
                .addProgressLine(recipeMapWorkable.getProgressPercent());
    }
}
