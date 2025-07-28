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
import gregtech.common.blocks.MetaBlocks;
import gtb.api.recipes.GTBRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.StainlessSteel;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityBallMill extends RecipeMapMultiblockController {

    public MetaTileEntityBallMill(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.BALL_MILL_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("~~~~~~~~~~~", "C~~~~~~~~~C", "C~~~~~~~~~C", "C~~~~~~~~~S", "~~~~~~~~~~~")
                .aisle("~~~~~~~~~~~", "~~~~~~~~~~~", "C~~~~~~~~~C", "~~~~~~~~~~~", "~~~~~~~~~~~")
                .aisle("~~~~~~~~~~~", "~~~~~~~~~~~", "F~PPPPPPP~F", "~~~~~~~~~~~", "~~~~~~~~~~~")
                .aisle("~~~~~~~~~~~", "~~PPPPPPP~~", "F~P~~~~~P~F", "~~PPPPPPP~~", "~~~~~~~~~~~")
                .aisle("~~PPPPPPP~~", "~~P~~~~~P~~", "FFP~~~~~PFF", "~~P~~~~~P~~", "~~PPPPPPP~~")
                .aisle("~~~~~~~~~~~", "~~PPPPPPP~~", "~~P~~~~~P~~", "~~PPPPPPP~~", "~~~~~~~~~~~")
                .aisle("~~~~~~~~~~~", "~~~~~~~~~~~", "~~PPPPPPP~~", "~~~~~~~~~~~", "~~~~~~~~~~~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState())
                        .or(autoAbilities()))
                .where('F', frames(Steel))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
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
        return new MetaTileEntityBallMill(metaTileEntityId);
    }
}
