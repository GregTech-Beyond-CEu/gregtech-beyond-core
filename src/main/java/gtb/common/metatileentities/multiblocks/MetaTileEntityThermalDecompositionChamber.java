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
import gtb.api.render.GTBTextures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class MetaTileEntityThermalDecompositionChamber extends RecipeMapMultiblockController {

    public MetaTileEntityThermalDecompositionChamber(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.THERMAL_DECOMPOSITION_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityThermalDecompositionChamber(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.FRONT, RelativeDirection.UP)
                .aisle("F~Z~F", "~~~~~", "~~~~~", "F~Z~F", "~~~~~", "~~P~~", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "~~P~~", "~~P~~", "~~P~~", "F~Z~F")
                .aisle("FZZZF", "~~P~~", "~~P~~", "FZZZF", "~~P~~", "~P~P~", "CCCCC", "C~~~C", "C~~~C", "C~~~C", "CCCCC", "~P~P~", "~P~P~", "~P~P~", "FZZZF")
                .aisle("F~Z~F", "~~~~~", "~~~~~", "F~Z~F", "~~~~~", "~~P~~", "CCCCC", "C~~~C", "C~~~C", "C~~~C", "CCCCC", "~~P~~", "~~P~~", "~~P~~", "F~Z~F")
                .aisle("~FFF~", "~~~~~", "~~~~~", "~FFF~", "~~~~~", "~~~~~", "CCCCC", "C~~~C", "C~~~C", "C~~~C", "CCCCC", "~~~~~", "~~~~~", "~~~~~", "~FFF~")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~~~~", "~~~~~", "~~~~~", "CCSCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "~~~~~", "~~~~~", "~~~~~", "~~~~~")
                .where('~', any())
                .where('S', selfPredicate())
                .where('C', states(getCasingState()).setMinGlobalLimited(85).or(autoAbilities()))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('F', frames(Materials.StainlessSteel))
                .where('Z', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    private static IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.HEAT_PROOF_CASING;
    }
}
