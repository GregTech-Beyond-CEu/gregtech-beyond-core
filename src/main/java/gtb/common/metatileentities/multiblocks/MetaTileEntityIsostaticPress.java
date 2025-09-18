package gtb.common.metatileentities.multiblocks;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.recipes.GTBRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.Steel;
import static gregtech.api.unification.material.Materials.TungstenSteel;

public class MetaTileEntityIsostaticPress extends RecipeMapMultiblockController {

    public MetaTileEntityIsostaticPress(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.ISOSTATIC_PRESS_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.BACK)
                .aisle("~PPP~", "~F~F~", "~F~F~", "~F~F~", "~F~F~", "~F~F~", "~PPP~", "~PCP~", "~PPP~", "~~~~~")
                .aisle("PCCCP", "F~~~F", "FCGCF", "FCGCF", "FCCCF", "FCCCF", "P~~~P", "P~~~P", "P~~~P", "~PPP~")
                .aisle("PCCCP", "~~~~~", "~GGG~", "~G~G~", "~C~C~", "~C~C~", "P~~~P", "C~~~C", "P~~~P", "~PPP~")
                .aisle("PCCCP", "F~~~F", "FCGCF", "FCGCF", "FCCCF", "FCCCF", "P~~~P", "P~~~P", "P~~~P", "~PPP~")
                .aisle("~PSP~", "~F~F~", "~F~F~", "~F~F~", "~F~F~", "~F~F~", "~PPP~", "~PCP~", "~PPP~", "~~~~~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(30)
                        .or(autoAbilities()))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('F', frames(TungstenSteel))
                .where('G', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.ROBUST_TUNGSTENSTEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.POLARIZER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityIsostaticPress(metaTileEntityId);
    }
}
