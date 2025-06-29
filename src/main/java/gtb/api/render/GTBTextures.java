package gtb.api.render;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

import gtb.GregtechBeyondCore;

@Mod.EventBusSubscriber(modid = GregtechBeyondCore.MODID, value = Side.CLIENT)
public final class GTBTextures {

    public static SimpleOverlayRenderer VACUUM_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing/vacuum_furnace_casing");
    public static SimpleOverlayRenderer SINTERING_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing/sintering_oven_casing");
    public static SimpleOverlayRenderer CLIMATE_PROOF_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing/climate_proof_casing");
    public static SimpleOverlayRenderer BIOLOGICALLY_STERILE_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing/biologically_sterile_casing");
    public static final OrientedOverlayRenderer CRYSTALLIZER_OVERLAY = new OrientedOverlayRenderer(
            "machines/crystallizer");
    public static OrientedOverlayRenderer DEHYDRATOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/dehydrator");
    public static OrientedOverlayRenderer NANOSCALE_FABRICATOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/nanoscale_fabricator");
    public static SimpleOverlayRenderer SILICON_CARBIDE_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing/silicon_carbide_casing");
    public static SimpleOverlayRenderer LEAD_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing/lead_casing");
    public static SimpleOverlayRenderer HSS_G_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing2/hss_g_casing");
    public static SimpleOverlayRenderer THERMAL_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing2/thermal_evaporation_casing");
    public static SimpleOverlayRenderer SONCIATOR_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing2/sonicator_casing");
    public static SimpleOverlayRenderer CVD_UNIT_OVERLAY = new SimpleOverlayRenderer(
            "multiblock/cvd_unit");
    public static SimpleOverlayRenderer LARGE_CVD_UNIT_OVERLAY = new SimpleOverlayRenderer(
            "multiblock/large_cvd_unit");
    public static SimpleOverlayRenderer CORROSION_PROOF_CARBON_STEEL_CASING = new SimpleOverlayRenderer(
            "gtb_multiblock_casing/corrosion_proof_carbon_steel_casing");
    public static final OrientedOverlayRenderer CURING_OVEN_OVERLAY = new OrientedOverlayRenderer(
            "machines/curing_oven");
    public static OrientedOverlayRenderer BEAM_SHRINKING_DEVICE_OVERLAY = new OrientedOverlayRenderer(
            "machines/beam_shrinking_device");
    public static OrientedOverlayRenderer VACUUM_EJECTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/vacuum_ejector");
    public static OrientedOverlayRenderer DRYER_OVERLAY = new OrientedOverlayRenderer(
            "machines/dryer");
    public static OrientedOverlayRenderer ROASTER_OVERLAY = new OrientedOverlayRenderer(
            "machines/roasters");
    public static OrientedOverlayRenderer ROTARY_EVAPORATOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/rotary_evaporator");
    public static OrientedOverlayRenderer MICROSCOPE_OVERLAY = new OrientedOverlayRenderer(
            "machines/microscope");
    public static OrientedOverlayRenderer BATCH_REACTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/batch_reactor");
    public static OrientedOverlayRenderer CONTINOUS_STIRRING_TANK_REACTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/cstr");
    public static OrientedOverlayRenderer TRICKLE_BED_REACTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/trickle_bed_reactor");
    public static OrientedOverlayRenderer WATER_COLLECTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/water_collector");
    public static OrientedOverlayRenderer INDUCTION_SMELTER_OVERLAY = new OrientedOverlayRenderer(
            "machines/induction_smelter");
    public static OrientedOverlayRenderer INSCRIBER_OVERLAY = new OrientedOverlayRenderer(
            "machines/inscriber");
    public static OrientedOverlayRenderer ION_EXCHANGE_UNIT_OVERLAY = new OrientedOverlayRenderer(
            "machines/ion_exchange_unit");
    public static OrientedOverlayRenderer UV_LIGHT_OVERLAY = new OrientedOverlayRenderer(
            "machines/uv_light");
    public static OrientedOverlayRenderer FLUID_COMPRESSOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/fluid_compressor");
    public static OrientedOverlayRenderer PLASMA_ETCHER_OVERLAY = new OrientedOverlayRenderer(
            "machines/plasma_etcher");
    public static OrientedOverlayRenderer BIO_REACTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/bio_reactor");
    public static OrientedOverlayRenderer ION_IMPLANTER_OVERLAY = new OrientedOverlayRenderer(
            "machines/ion_implanter");
    public static OrientedOverlayRenderer ELECTRON_BEAM_LITHOGRAPHER_OVERLAY = new OrientedOverlayRenderer(
            "machines/electron_beam");
    public static SimpleOverlayRenderer PORCELAIN_TILES = new SimpleOverlayRenderer(
            "gtb_multiblock_casing2/galvanized_porcelain_tiles");

    private GTBTextures() {}

    public static void preInit() {}
}
