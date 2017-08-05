package net.b0at.antikick.util;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by Jordin on 8/5/2017.
 * Jordin is still best hacker.
 */
public class AntiKickConfig {
    public String prefix = "&7[&9B0AT&7] &f"; // TODO: Add 'prefix' in the config for B0ATBasePlugin and then use a messagePlayer in the base plugin.
    public String preventedKickMessage = "&cPrevented kick: &f%s";

    public List<String> allowedKickReasons = ImmutableList.of("Kicked from server.", "banned");
}
