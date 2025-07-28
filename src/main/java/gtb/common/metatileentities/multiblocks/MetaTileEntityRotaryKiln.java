package gtb.common.metatileentities.multiblocks;

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
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.recipes.GTBRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class MetaTileEntityRotaryKiln extends RecipeMapMultiblockController {

    public MetaTileEntityRotaryKiln(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.ROTARY_KILN_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityRotaryKiln(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("F~F", "~~~", "~~~", "~~~", "~~~", "~~~", "~~~", "~~~", "~~~", "F~F")
                .aisle("F~F", "~~~", "~~~", "~~~", "~~~", "~~~", "~~~", "~~~", "~~~", "F~F")
                .aisle("FCF", "~C~", "~C~", "~C~", "~C~", "~C~", "~C~", "~C~", "~C~", "FCF")
                .aisle("CCC", "C~C", "C~C", "C~C", "C~C", "C~C", "C~C", "C~C", "C~C", "CSC")
                .aisle("~C~", "~C~", "~C~", "~C~", "~C~", "~C~", "~C~", "~C~", "~C~", "~C~")
                .where('~', any())
                .where('S', selfPredicate())
                .where('C', states(getCasingState()).setMinGlobalLimited(40).or(autoAbilities()))
                .where('F', frames(Materials.Steel))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    private static IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.PRIMITIVE_BRICKS;
    }
}
