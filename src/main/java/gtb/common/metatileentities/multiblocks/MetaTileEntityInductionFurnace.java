package gtb.common.metatileentities.multiblocks;

import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
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
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.recipes.GTBRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class MetaTileEntityInductionFurnace extends RecipeMapMultiblockController {

    public MetaTileEntityInductionFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.INDUCTION_FURNACE_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("~~F~F~~", "~~CCC~~", "FCCCCCF", "~CCCCC~", "FCCCCCF", "~~CSC~~", "~~F~F~~")
                .aisle("~~F~F~~", "~~CCC~~", "FC~~~CF", "~C~~~C~", "FC~~~CF", "~~CCC~~", "~~F~F~~")
                .aisle("~~F~F~~", "~~CCC~~", "FC~~~CF", "~C~~~C~", "FC~~~CF", "~~CCC~~", "~~F~F~~")
                .aisle("~~~~~~~", "~~FFF~~", "~FCCCF~", "~FC~CF~", "~FCCCF~", "~~FFF~~", "~~~~~~~")
                .aisle("~~~~~~~", "~~~~~~~", "~~CCC~~", "~~C~C~~", "~~CCC~~", "~~~~~~~", "~~~~~~~")
                .aisle("~~~~~~~", "~~~~~~~", "~~CCC~~", "~~C~C~~", "~~CCC~~", "~~~~~~~", "~~~~~~~")
                .aisle("~~~~~~~", "~~~~~~~", "~~CCC~~", "~~C~C~~", "~~CCC~~", "~~~~~~~", "~~~~~~~")
                .aisle("~~~~~~~", "~~FFF~~", "~FZZZF~", "~FZZZF~", "~FZZZF~", "~~FFF~~", "~~~~~~~")
                .where('S', selfPredicate())
                .where('Z',
                        states(GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT)))
                .where('~', any())
                .where('F', frames(Materials.Steel))
                .where('C', states(getCasingState()).setMinGlobalLimited(55)
                        .or(autoAbilities()))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.HEAT_PROOF_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityInductionFurnace(metaTileEntityId);
    }
}
