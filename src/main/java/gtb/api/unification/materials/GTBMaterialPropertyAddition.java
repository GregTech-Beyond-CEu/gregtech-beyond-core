package gtb.api.unification.materials;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.FORCE_GENERATE_BLOCK;
import static gtb.api.unification.materials.GTBMaterials.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.properties.*;
import supercritical.api.unification.material.properties.*;
public class GTBMaterialPropertyAddition {

    public static void init() {
        Bromine.setProperty(PropertyKey.FLUID,
                new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        AmmoniumChloride.setProperty(PropertyKey.FLUID,
                new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Sodium.setProperty(PropertyKey.FLUID,
                new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Cadmium.setProperty(PropertyKey.FLUID,
                new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        PhosphorusPentoxide.setProperty(PropertyKey.FLUID,
                new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        SodiumSulfide.setProperty(PropertyKey.FLUID,
                new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Salt.setProperty(PropertyKey.FLUID,
                new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        Selenium.setProperty(PropertyKey.DUST, new DustProperty());
        Iron3Chloride.setProperty(PropertyKey.DUST, new DustProperty());
        Tellurium.setProperty(PropertyKey.DUST, new DustProperty());
        Moscovium.setProperty(PropertyKey.DUST, new DustProperty());
        Rubidium.setProperty(PropertyKey.DUST, new DustProperty());
        Nihonium.setProperty(PropertyKey.DUST, new DustProperty());
        Thallium.setProperty(PropertyKey.DUST, new DustProperty());
        Iodine.setProperty(PropertyKey.DUST, new DustProperty());
        Mica.setProperty(PropertyKey.INGOT, new IngotProperty());
        BandedIron.setProperty(PropertyKey.INGOT, new IngotProperty());
        PlatinumRaw.setProperty(PropertyKey.INGOT, new IngotProperty());



        Thulium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Terbium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Graphene

        Graphene.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Holmium

        Holmium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Hafnium

        Hafnium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Scandium

        Scandium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Dysprosium

        Dysprosium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Erbium

        Erbium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Gadolinium

        Gadolinium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Rhenium

        Rhenium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Strontium

        Strontium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Germanium

        Germanium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Rutherfordium

        Rutherfordium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Rubidium

        Rubidium.setProperty(PropertyKey.INGOT, new IngotProperty());

        Uraninite.setProperty(SCPropertyKey.FISSION_FUEL,
                FissionFuelProperty.builder(Uraninite.getRegistryName(), 1800, 60000, 2.4)
                        .fastNeutronCaptureCrossSection(0.5)
                        .slowNeutronCaptureCrossSection(1)
                        .slowNeutronFissionCrossSection(1)
                        .requiredNeutrons(1)
                        .releasedNeutrons(2.5)
                        .releasedHeatEnergy(0.01)
                        .decayRate(0.001)
                        .build());

        DistilledWater.setProperty(SCPropertyKey.COOLANT,
                new CoolantProperty(DistilledWater, HighPressureSteam, FluidStorageKeys.LIQUID, 2., 1000,
                        373, 2260000, 4168.)
                        .setAccumulatesHydrogen(true).setSlowAbsorptionFactor(0.1875)
                        .setFastAbsorptionFactor(0.0625));

        Graphite.addFlags(FORCE_GENERATE_BLOCK);

        Graphite.setProperty(SCPropertyKey.MODERATOR, ModeratorProperty.builder()
                .maxTemperature(3650)
                .absorptionFactor(0.0625)
                .moderationFactor(3).build());

        Beryllium.setProperty(SCPropertyKey.MODERATOR, ModeratorProperty.builder()
                .maxTemperature(1500)
                .absorptionFactor(0.015625)
                .moderationFactor(5).build());
    }
}
