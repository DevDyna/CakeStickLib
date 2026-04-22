
package com.devdyna.cakesticklib.api.recipe.recipeBuilder;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class FluidAttach {
    public class Input {
        public static interface SizedFluid<BUILDER extends BaseRecipeBuilder> extends Any.SimpleFluidAttach<BUILDER> {

            abstract BUILDER fluid(SizedFluidIngredient fluid);

            default BUILDER fluid(TagKey<Fluid> fluid, int amount, HolderLookup.Provider p) {
                return fluid(x.fluidSized(fluid, amount, p));
            }

            default BUILDER fluid(TagKey<Fluid> fluid, HolderLookup.Provider p) {
                return fluid(x.fluidSized(fluid, 1000, p));
            }

            default BUILDER fluid(Fluid fluid) {
                return fluid(x.fluidSized(fluid));
            }

            default BUILDER fluid(Fluid fluid, int c) {
                return fluid(x.fluidSized(fluid, c));
            }

        }

        public static interface DoubleSizedFluid<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER fluids(SizedFluidIngredient a, SizedFluidIngredient b);

            default BUILDER fluids(TagKey<Fluid> a, int a_amount, TagKey<Fluid> b, int b_amount,
                    HolderLookup.Provider p) {
                return fluids(x.fluidSized(a, a_amount, p), x.fluidSized(b, b_amount, p));
            }

            default BUILDER fluids(Fluid a, int a_amount, Fluid b, int b_amount) {
                return fluids(x.fluidSized(a, a_amount), x.fluidSized(b, b_amount));
            }

            default BUILDER fluids(TagKey<Fluid> a, TagKey<Fluid> b, HolderLookup.Provider p) {
                return fluids(x.fluidSized(a, 1000, p), x.fluidSized(b, 1000, p));
            }

            default BUILDER fluids(Fluid a, Fluid b) {
                return fluids(x.fluidSized(a, 1000), x.fluidSized(b, 1000));
            }

        }

    }

    public class Any {
        public static interface SimpleFluidAttach<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER fluid(Fluid fluid, int amount);

            default BUILDER fluid(Fluid fluid) {
                return fluid(fluid, 1000);
            }

        }
    }

    public class Output {
        public static interface OutputFluid<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER output(FluidStackTemplate fluid);

            default BUILDER output(Fluid fluid, int amount) {
                return output(x.fluidTemplate(fluid, amount));
            }

            default BUILDER output(Fluid fluid) {
                return output(fluid, 1000);
            }

        }
    }

}