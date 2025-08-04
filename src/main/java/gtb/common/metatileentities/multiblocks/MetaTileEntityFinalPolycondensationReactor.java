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
import gregtech.common.blocks.*;
import gtb.api.recipes.GTBRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityFinalPolycondensationReactor extends RecipeMapMultiblockController {

    public MetaTileEntityFinalPolycondensationReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.FINAL_POLYCONDENSATION_REACTOR_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.DOWN, RelativeDirection.BACK)
                .aisle("~~~~~", "~~~~~", "~PPP~", "~~~~~", "~PPP~", "~~~~~", "~~~~~", "~FFF~", "~~F~~", "~~F~~", "~~F~~", "~FFF~")
                .aisle("~~C~~", "~CCC~", "PCCCP", "~CCC~", "PCCCP", "~CCC~", "~CCC~", "FCCCF", "~CCC~", "~~~~~", "~~~~~", "F~~~F")
                .aisle("~CCC~", "~CCC~", "PCCCP", "~CCC~", "PCCCP", "~CCC~", "~CCC~", "FCCCF", "FCCCF", "F~~~F", "F~~~F", "F~~~F")
                .aisle("~~C~~", "~CCC~", "PCCCP", "~CCC~", "PCCCP", "~CCC~", "~CSC~", "FCCCF", "~CCC~", "~~~~~", "~~~~~", "F~~~F")
                .aisle("~~~~~", "~~~~~", "~PPP~", "~~~~~", "~PPP~", "~~~~~", "~~~~~", "~FFF~", "~~F~~", "~~F~~", "~~F~~", "~FFF~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(40)
                        .or(autoAbilities()))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('F', frames(Steel))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityFinalPolycondensationReactor(metaTileEntityId);
    }
}
