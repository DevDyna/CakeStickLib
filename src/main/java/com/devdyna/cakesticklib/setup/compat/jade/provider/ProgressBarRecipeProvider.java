package com.devdyna.cakesticklib.setup.compat.jade.provider;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.TickerDelay;
import com.devdyna.cakesticklib.api.aspect.logic.TickerDelay.TimerStyle;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.resources.Identifier;
import snownee.jade.api.*;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.view.*;
import snownee.jade.impl.ui.JadeUIInternal;

public class ProgressBarRecipeProvider implements IServerExtensionProvider<ProgressView.Data>,
    IClientExtensionProvider<ProgressView.Data, ProgressView> {

  public static final Identifier UUID = x.rl(MODULE_ID, "progressbar_delay");

  public static final ProgressBarRecipeProvider INSTANCE = new ProgressBarRecipeProvider();

  @Override
  public @Nullable List<ViewGroup<ProgressView.Data>> getGroups(Accessor<?> a) {
    return (a instanceof BlockAccessor b)
        && (b.getBlockEntity() instanceof TickerDelay t)
        && (TimerStyle.isProgressBar(t))
        && (t.getTicker() != null)
            ? List.of(new ViewGroup<>(
                List.of(new ProgressView.Data(t.getTicker().percent()))))
            : null;
  }

  @Override
  public List<ClientViewGroup<ProgressView>> getClientGroups(Accessor<?> a,
      List<ViewGroup<ProgressView.Data>> l) {
    return ClientViewGroup.map(l,
        data -> new ProgressView(ProgressView.Part.of(data.progress(), ColorUtils.rainbow().getRGB()), null,
            JadeUIInternal.progressStyle(), BoxStyle.nestedBox()),
        null);
  }

  @Override
  public boolean shouldRequestData(Accessor<?> a) {
    return (a instanceof BlockAccessor b
        && b.getBlockEntity() instanceof TickerDelay t
        && (TimerStyle.isProgressBar(t)));
  }

  @Override
  public Identifier getUid() {
    return ProgressBarRecipeProvider.UUID;
  }

}
