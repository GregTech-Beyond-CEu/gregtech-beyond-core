package gtb.common.metatileentities.multiblocks;


import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.HeatingCoilRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.metatileentities.MetaTileEntities;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing;
import gtb.common.metatileentities.GTBMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import static gtb.api.unification.materials.GTBMaterials.BlazingPyrotheum;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;

public class MetaTileEntityBlazingBlastFurnace extends RecipeMapMultiblockController implements IHeatingCoil {
    private int blastFurnaceTemperature;
    protected static int heatingCoilLevel;
    protected int heatingCoilDiscount;

    public MetaTileEntityBlazingBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, BLAST_RECIPES);
        this.recipeMapWorkable = new BlazingBlastRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityBlazingBlastFurnace(this.metaTileEntityId);
    }

    //  CoilType Context
    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("CoilType");
        Object coilType = context.get("CoilType");
        if (type instanceof IHeatingCoilBlockStats) {
            this.blastFurnaceTemperature = ((IHeatingCoilBlockStats) type).getCoilTemperature();
            this.heatingCoilLevel = ((IHeatingCoilBlockStats) coilType).getLevel();
            this.heatingCoilDiscount = ((IHeatingCoilBlockStats) coilType).getEnergyDiscount();
        } else {
            this.blastFurnaceTemperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();
            this.heatingCoilLevel = BlockWireCoil.CoilType.CUPRONICKEL.getLevel();
            this.heatingCoilDiscount = BlockWireCoil.CoilType.CUPRONICKEL.getEnergyDiscount();
        }

        this.blastFurnaceTemperature += 100 * Math.max(0, GTUtility.getTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);
    }

    //  Check recipe
    @Override
    public boolean checkRecipe(@Nonnull Recipe recipe, boolean consumeIfSuccess) {
        return this.blastFurnaceTemperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0);
    }

    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "CCC", "XXX")
                .aisle("XXX", "C#C", "C#C", "XMX")
                .aisle("XSX", "CCC", "CCC", "XXX")
                .where('S', this.selfPredicate())
                .where('X', states(new IBlockState[]{this.getCasingState()})
                        .setMinGlobalLimited(9)
                        .or(this.autoAbilities(true, true, true, true, true, true, false)))
                .where('M', abilities(new MultiblockAbility[]{MultiblockAbility.MUFFLER_HATCH}))
                .where('C', heatingCoils())
                .where('#', air())
                .build();
    }

    private static IBlockState getCasingState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING.getState(GTBMultiblockCasing.CasingType.ADVANCED_INVAR_CASING);
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = null;
        if (Blocks.AIR != null) {
            builder = MultiblockShapeInfo.builder()
                    .aisle("EEM", "CCC", "CCC", "XXX")
                    .aisle("FXD", "C#C", "C#C", "XHX")
                    .aisle("ISO", "CCC", "CCC", "XXX")
                    .where('S', GTBMetaTileEntities.BLAZING_BLAST_FURNACE, EnumFacing.SOUTH)
                    .where('X', getCasingState())
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[5], EnumFacing.NORTH)
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.SOUTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                    .where('D', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.EAST)
                    .where('H', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('M', () -> {
                        return ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : GTBMetaBlocks.GTB_MULTIBLOCK_CASING.getState(GTBMultiblockCasing.CasingType.ADVANCED_INVAR_CASING);
                    }, EnumFacing.NORTH);
        }
        MultiblockShapeInfo.Builder finalBuilder = builder;
        GregTechAPI.HEATING_COILS.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getTier()))
                .forEach(entry -> {
                    if (finalBuilder != null) {
                        shapeInfo.add(finalBuilder.where('C', entry.getKey()).build());
                    }
                });
        return shapeInfo;
    }

    //  Tooltips
    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"));
        tooltip.add(I18n.format("epimorphism.machine.blazing_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("epimorphism.machine.blazing_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("epimorphism.machine.blazing_blast_furnace.tooltip.3"));
        tooltip.add(I18n.format("epimorphism.machine.blazing_blast_furnace.tooltip.4"));
    }

    //  Display Texts
    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {

            if (getInputFluidInventory() != null) {
                FluidStack LubricantStack = getInputFluidInventory().drain(BlazingPyrotheum.getFluid(Integer.MAX_VALUE), false);
                int liquidOxygenAmount = LubricantStack == null ? 0 : LubricantStack.amount;
                textList.add(new TextComponentTranslation("epimorphism.machine.blazing_blast_furnace.amount", TextFormattingUtil.formatNumbers((liquidOxygenAmount))));
                textList.add(new TextComponentTranslation("epimorphism.machine.blazing_blast_furnace.heat"));
            }
            textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature", blastFurnaceTemperature)
                    .setStyle(new Style().setColor(TextFormatting.RED)));
            textList.add(new TextComponentTranslation("gregtech.multiblock.multi_furnace.heating_coil_level", heatingCoilLevel));
            textList.add(new TextComponentTranslation("gregtech.multiblock.multi_furnace.heating_coil_discount", heatingCoilDiscount));
        }
        super.addDisplayText(textList);
    }

    //  Warning
    @Override
    protected void addWarningText(List<ITextComponent> textList) {
        super.addWarningText(textList);
        if (isStructureFormed()) {
            FluidStack lubricantStack = getInputFluidInventory().drain(BlazingPyrotheum.getFluid(Integer.MAX_VALUE), false);
            if (lubricantStack == null || lubricantStack.amount == 0) {
                textList.add(new TextComponentTranslation("epimorphism.machine.blazing_blast_furnace.warning"));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.HEAT_PROOF_CASING;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.BLAST_FURNACE_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public int getCurrentTemperature() {
        return this.blastFurnaceTemperature;
    }

    /**  getMaxParallel(int heatingCoilLevel)
     *     @param heatingCoilLevel the level to get the parallel for
     *     @return the max parallel for the heating coil level
     */
    public static int getMaxParallel(int heatingCoilLevel) {
        return heatingCoilLevel;
    }

    private final FluidStack BLAZING_STACK = BlazingPyrotheum.getFluid(2);

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        blastFurnaceTemperature = 0;
        heatingCoilLevel = 0;
        heatingCoilDiscount = 0;
    }

    protected class BlazingBlastRecipeLogic extends HeatingCoilRecipeLogic {

        private final MetaTileEntityBlazingBlastFurnace blastFurnace;
        public BlazingBlastRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.blastFurnace = (MetaTileEntityBlazingBlastFurnace) tileEntity;
        }

        @Override
        public int getParallelLimit() {
            return getMaxParallel(heatingCoilLevel);
        }

        public void setMaxProgress(int maxProgress) {
            this.maxProgressTime = maxProgress / 2;
        }

        protected void updateRecipeProgress() {
            if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
                IMultipleTankHandler inputTank = blastFurnace.getInputFluidInventory();
                if (BLAZING_STACK.isFluidStackIdentical(inputTank.drain(BLAZING_STACK, false))) {
                    inputTank.drain(BLAZING_STACK, true);
                    if (++progressTime > maxProgressTime) {
                        completeRecipe();
                    }
                }
                else return;
                drawEnergy(recipeEUt, false);
            }
        }
    }
}
