package gtb.common.metatileentities.multiblocks;

import static gtb.common.block.blocks.GTBMultiblockCasing.CasingType.ADVANCED_SUBSTRATE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.*;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityItemBus;

import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;

import gtb.api.recipes.GTBRecipeMaps;
import gtb.api.render.GTBTextures;
import gtb.api.utils.IsBetweenUtils;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.BlockCrucible;
import gtb.common.metatileentities.GTBMetaTileEntities;

public class MetaTileEntityNanoscaleFabricator extends RecipeMapMultiblockController {

    private int temperature;

    public MetaTileEntityNanoscaleFabricator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.MOLECULAR_BEAM_RECIPES);
        this.recipeMapWorkable = new NanoscaleFabricatorWorkableHandler(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityNanoscaleFabricator(metaTileEntityId);
    }

    @Override
    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
                .aisle("  XXXXX  ", "  XX#XX  ", "  XXXXX  ", "  XXXXX  ")
                .aisle(" XXXXXXX ", " X#####X ", " X#####X ", " XXGGGXX ")
                .aisle("TXXTTTXXT", "TX#####XT", "TX#####XT", " XGGGGGX ")
                .aisle("TXXTITXXT", "I###A###I", "CX#####XC", " XGGGGGX ")
                .aisle("TXXTTTXXT", "TX#####XT", "TX#####XT", " XGGGGGX ")
                .aisle(" XXXXXXX ", " X#####X ", " X#####X ", " XXGGGXX ")
                .aisle("  XXXXX  ", "  XX#XX  ", "  XXXXX  ", "  XXSXX  ")
                .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
                .where('S', selfPredicate())
                .where('X',
                        states(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                                .getState(BlockLargeMultiblockCasing.CasingType.ENGRAVER_CASING))
                                        .setMinGlobalLimited(84)
                                        .or(autoAbilities(true, true, true, true, true, true, true)))
                .where('T', states(getNonconductingState()).setMinGlobalLimited(36))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('I',
                        metaTileEntities(MetaTileEntities.ITEM_IMPORT_BUS[GTValues.ULV])
                                .or(states(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                                        .getState(BlockLargeMultiblockCasing.CasingType.NONCONDUCTING_CASING))))
                .where('C', states(getNonconductingState()).or(cruciblePredicate()))
                .where('A',
                        states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING
                                .getState(ADVANCED_SUBSTRATE)))
                .where('#', air())
                .where(' ', any())
                .build();
    }

    private IBlockState getNonconductingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                .getState(BlockLargeMultiblockCasing.CasingType.NONCONDUCTING_CASING);
    }

    @Nonnull
    private TraceabilityPredicate cruciblePredicate() {
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState state = blockWorldState.getBlockState();
            Block block = state.getBlock();
            if (block instanceof BlockCrucible) {
                int storedTemperature = blockWorldState.getMatchContext().getOrPut("Temperature", 0);
                blockWorldState.getMatchContext().set("Temperature",
                        ((BlockCrucible) block).getState(state).getTemperature() + storedTemperature);

                int storedCrucibleAmount = blockWorldState.getMatchContext().getOrPut("CrucibleAmount", 0);
                blockWorldState.getMatchContext().set("CrucibleAmount", 1 + storedCrucibleAmount);
                return true;
            }
            return false;
        }, () -> Arrays.stream(BlockCrucible.CrucibleType.values())
                .sorted(Comparator.comparingInt(BlockCrucible.CrucibleType::getTemperature))
                .map(type -> new BlockInfo(GTBMetaBlocks.BLOCK_CRUCIBLE.getState(type), null))
                .toArray(BlockInfo[]::new));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int crucibleAmount = context.getInt("CrucibleAmount");
        if (crucibleAmount != 0) this.temperature = context.getInt("Temperature") / crucibleAmount;
        else this.temperature = 0;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.temperature = 0;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        if (iMultiblockPart instanceof MetaTileEntityItemBus &&
                ((MetaTileEntityItemBus) iMultiblockPart).getExportItems().getSlots() == 0) {
            return GCYMTextures.NONCONDUCTING_CASING;
        }
        return GCYMTextures.ENGRAVER_CASING;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GTBTextures.NANOSCALE_FABRICATOR_OVERLAY;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature",
                    TextFormatting.RED + TextFormattingUtil.formatNumbers(temperature) + "K"));
        }
        super.addDisplayText(textList);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtb.multiblock.nanoscale_fabricator.tooltip.1"));
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle("   TTT   ", "   TPT   ", "   TCT   ", "         ")
                .aisle("  XXXXX  ", "  XX#XX  ", "  XXXXX  ", "  XXXXX  ")
                .aisle(" XXXXXXX ", " X     X ", " X     X ", " XXGGGXX ")
                .aisle("TXXTTTXXT", "TX     XT", "TX     XT", " XGGGGGX ")
                .aisle("TXXTATXXT", "N   J   U", "CX     XC", " XGGGGGX ")
                .aisle("TXXTTTXXT", "TX     XT", "TX     XT", " XGGGGGX ")
                .aisle(" XXXXXXX ", " X     X ", " X     X ", " XXGGGXX ")
                .aisle("  XXXXX  ", "  FX XO  ", "  LXXXX  ", "  XMSEX  ")
                .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
                .where('S', GTBMetaTileEntities.NANOSCALE_FABRICATOR, EnumFacing.SOUTH)
                .where('X',
                        GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                                .getState(BlockLargeMultiblockCasing.CasingType.ENGRAVER_CASING))
                .where('T',
                        GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                                .getState(BlockLargeMultiblockCasing.CasingType.NONCONDUCTING_CASING))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS))
                .where('J',
                        GTBMetaBlocks.GTB_MULTIBLOCK_CASING
                                .getState(ADVANCED_SUBSTRATE))
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.ULV], EnumFacing.SOUTH)
                .where('N', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.ULV], EnumFacing.WEST)
                .where('P', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.ULV], EnumFacing.NORTH)
                .where('U', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.ULV], EnumFacing.EAST)
                .where('A', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.ULV], EnumFacing.DOWN)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LuV], EnumFacing.SOUTH)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.SOUTH)
                .where(' ', Blocks.AIR.getDefaultState());

        if (ConfigHolder.machines.enableMaintenance)
            builder.where('M', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.SOUTH);
        else
            builder.where('M', GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                    .getState(BlockLargeMultiblockCasing.CasingType.ENGRAVER_CASING));

        for (BlockCrucible.CrucibleType crucibleType : BlockCrucible.CrucibleType.values()) {
            shapeInfos.add(builder.where('C', GTBMetaBlocks.BLOCK_CRUCIBLE.getState(crucibleType)).build());
        }

        return shapeInfos;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class NanoscaleFabricatorWorkableHandler extends MultiblockRecipeLogic {

        public NanoscaleFabricatorWorkableHandler(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        public boolean checkRecipe(@Nonnull Recipe recipe) {
            int delta = temperature - recipe.getProperty(TemperatureProperty.getInstance(), 0);
            return IsBetweenUtils.isBetweenInclusive(0, 250, delta);
        }
    }
}
