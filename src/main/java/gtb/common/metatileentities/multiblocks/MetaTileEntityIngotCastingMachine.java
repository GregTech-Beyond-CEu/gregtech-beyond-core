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
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.recipes.GTBRecipeMaps;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing2;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityIngotCastingMachine extends RecipeMapMultiblockController {

    public MetaTileEntityIngotCastingMachine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.INGOT_CASTING_RECIPES);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.DOWN, RelativeDirection.BACK)
                .aisle("CCCCC", "CCCCC", "F~~~F", "F~~~F", "F~~~F")
                .aisle("C~~~C", "C~~~C", "~CCC~", "~~~~~", "~~~~~")
                .aisle("CCCCC", "CCCCC", "FCCCF", "F~~~F", "F~~~F")
                .aisle("~~~~~", "~~~~~", "FPPPF", "~~~~~", "~~~~~")
                .aisle("~~~~~", "~~~~~", "~~~~~", "FPPPF", "~~~~~")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~~~~", "FPPPF")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~~~~", "FPPPF")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~~~~", "FPPPF")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~~~~", "FPPPF")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~~~~", "FPPPF")
                .aisle("~~~~~", "~~~~~", "~~~~~", "F~~~F", "FPPPF")
                .aisle("~~~~~", "~~~~~", "~~~~~", "CCCCC", "CCCCC")
                .aisle("~~~~~", "~~~~~", "~~~~~", "C~~~C", "CCCCC")
                .aisle("~~~~~", "~~~~~", "~~~~~", "C~~~C", "CCCCC")
                .aisle("~~~~~", "~~~~~", "~~~~~", "CCCCC", "CCSCC")
                .where('S', selfPredicate())
                .where('C',
                        states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                                .setMinGlobalLimited(25)
                                .or(autoAbilities()))
                .where('F', frames(Steel))
                .where('P', states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING2.getState(GTBMultiblockCasing2.CasingType.ROLLER)))
                .where('~', any())
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
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
        return new MetaTileEntityIngotCastingMachine(metaTileEntityId);
    }
}
