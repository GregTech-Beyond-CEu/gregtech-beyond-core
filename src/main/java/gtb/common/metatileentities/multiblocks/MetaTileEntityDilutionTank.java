package gtb.common.metatileentities.multiblocks;

import gtb.api.render.GTBTextures;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing3;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import gtb.api.recipes.GTBRecipeMaps;

public class MetaTileEntityDilutionTank extends RecipeMapMultiblockController {

    public MetaTileEntityDilutionTank(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.DILUTION_TANK_RECIPES);
    }

    public IBlockState getCasingState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING3.getState(GTBMultiblockCasing3.CasingType.FRP_LINED_STEEL_CASING);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.DOWN, RelativeDirection.BACK)
                .aisle("~~C~~", "~CGC~", "~GGG~", "~GGG~", "~CCC~", "~F~F~")
                .aisle("~CCC~", "C~~~C", "G~~~G", "G~~~G", "CCCCC", "F~~~F")
                .aisle("CCCCC", "G~~~G", "G~~~G", "G~~~G", "CCCCC", "F~~~F")
                .aisle("~CCC~", "C~~~C", "G~~~G", "G~~~G", "CCCCC", "F~~~F")
                .aisle("~~C~~", "~CGC~", "~GGG~", "~GGG~", "~CSC~", "~F~F~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(15).or(autoAbilities()))
                .where('F', frames(Materials.BlueSteel))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getDefaultState()))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTBTextures.FRP_LINED_STEEL_CASING_OVERLAY;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityDilutionTank(metaTileEntityId);
    }
}
