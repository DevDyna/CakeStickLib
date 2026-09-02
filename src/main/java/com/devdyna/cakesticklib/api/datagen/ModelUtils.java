package com.devdyna.cakesticklib.api.datagen;

import java.util.Optional;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.item.DynamicFluidContainerModel;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModelUtils {

        public static void itemSubFolder(DeferredRegister.Items items, String prefix, String replacer,
                        ItemModelGenerators g) {
                items.getEntries().forEach(i -> g.itemModelOutput.accept(i.get(),
                                ItemModelUtils.plainModel(
                                                ModelTemplates.FLAT_ITEM.create(
                                                                i.get(),
                                                                new TextureMapping()
                                                                                .put(TextureSlot.LAYER0, x.material(
                                                                                                x.rl(x.mod(i), x.name(i)
                                                                                                                .replace(replacer,
                                                                                                                                ""))
                                                                                                                .withPrefix(prefix))),
                                                                g.modelOutput))

                ));
        }

        public static void fluid(BlockModelGenerators b, Block fluid, String modid) {

                var model = new ModelTemplate(Optional.empty(), Optional.empty(),
                                TextureSlot.PARTICLE);

                var location = x.rl(fluid).withPrefix("block/");

                model.create(location, new TextureMapping().put(TextureSlot.PARTICLE,
                                getBlockTexture(modid, "fluid_source")), b.modelOutput);

                b.blockStateOutput.accept(
                                MultiVariantGenerator.dispatch(fluid, BlockModelGenerators.plainVariant(location)));
        }

        public static Material getBlockTexture(String modid, String b) {
                return getGenericTexture(modid, "block/" + b);
        }

        public static Material getItemTexture(String modid, String b) {
                return getGenericTexture(modid, "item/" + b);
        }

        /**
         * Use x.material(mod,path)
         */
        @Deprecated
        public static Material getGenericTexture(String modid, String b) {
                return x.material(modid, b);
        }

        public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas,
                        boolean coverIsMask, boolean applyFluidLuminosity,
                        boolean forceOpaqueFluid, Identifier container, Identifier mask) {
                var item = (BucketItem) bucket;
                itemModels.itemModelOutput.accept(item,
                                new DynamicFluidContainerModel.Unbaked(
                                                new DynamicFluidContainerModel.Textures(
                                                                Optional.empty(),
                                                                Optional.of(new Material(container)),
                                                                Optional.of(new Material(mask)),
                                                                Optional.empty()),
                                                item.content, flipGas, coverIsMask, applyFluidLuminosity,
                                                forceOpaqueFluid));

        }

        public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas,
                        boolean coverIsMask, boolean applyFluidLuminosity,
                        boolean forceOpaqueFluid) {
                createBucketItem(itemModels, bucket, flipGas, coverIsMask, applyFluidLuminosity, forceOpaqueFluid,
                                x.mcLoc("item/bucket"), x.rl("neoforge", "item/mask/bucket_fluid_drip"));

        }

        public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas,
                        boolean coverIsMask, boolean applyFluidLuminosity) {
                createBucketItem(itemModels, bucket, flipGas, coverIsMask, applyFluidLuminosity, true);
        }

        public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas,
                        boolean coverIsMask) {
                createBucketItem(itemModels, bucket, flipGas, coverIsMask, true);
        }

        public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas) {
                createBucketItem(itemModels, bucket, flipGas, false);
        }

        public static void createBucketItem(ItemModelGenerators itemModels, Item bucket) {
                createBucketItem(itemModels, bucket, false);
        }

}
