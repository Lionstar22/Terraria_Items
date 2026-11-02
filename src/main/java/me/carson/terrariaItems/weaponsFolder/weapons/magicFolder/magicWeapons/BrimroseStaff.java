package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.*;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.*;

public class BrimroseStaff extends Magic {

    private final Particle.DustOptions particle=new Particle.DustOptions(Color.fromRGB(155, 21, 18), 0.5f);
    private final double length=4.0;
    private record Node(Location loc, Vector dir) {}

    public BrimroseStaff(Plugin plugin) {
        super(plugin,"brimrose_staff.name","#FF96FF", Material.AMETHYST_SHARD,"brimrose_staff","BrimroseStaff",7,0f,5,0,0,12,"brimrose_staff.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            shoot(player);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_use", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BrimroseStaff(plugin).createItem();
    }

    private void shoot(Player player) {
        Location start = player.getEyeLocation().add(0, -0.1, 0);
        Vector dir = player.getEyeLocation().getDirection().normalize();
        Location next = start.clone().add(dir.clone().multiply(length));
        castLine(player, start, next);
        List<Node> current = new ArrayList<>();
        current.add(new Node(next, dir));
        for (int i = 0; i < 3; i++) {
            List<Node> nextGen = new ArrayList<>();
            for (Node node : current) {
                List<Vector> newDirs = branch(node.dir());
                Vector leftDir = newDirs.getFirst().normalize();
                Vector rightDir = newDirs.getLast().normalize();
                Location left = node.loc().clone().add(leftDir.clone().multiply(length));
                Location right = node.loc().clone().add(rightDir.clone().multiply(length));
                castLine(player, node.loc(), left);
                castLine(player, node.loc(), right);
                nextGen.add(new Node(left, leftDir));
                nextGen.add(new Node(right, rightDir));
            }
            current = nextGen;
        }
    }

    private void castLine(Player player,Location start,Location end){
        drawLine(player,start,end);
        for(LivingEntity entity:entitiesBetween(player,start,end,0.1)){
            DamageSource source = DamageSource.builder(DamageType.LIGHTNING_BOLT).withCausingEntity(player).withDirectEntity(player).build();
            entity.damage((damage),source);
        }
    }

    public List<LivingEntity> entitiesBetween(Player shooter, Location from, Location to, double raySize) {
        Vector start = from.toVector();
        Vector direction = to.toVector().subtract(start);
        double distance = direction.length();
        if (distance == 0) return List.of();
        direction.normalize();

        BoundingBox area = BoundingBox.of(from, to).expand(raySize);

        List<LivingEntity> hits = new ArrayList<>();
        for (Entity entity : from.getWorld().getNearbyEntities(area)) {
            if (!(entity instanceof LivingEntity living)) continue;
            if (living.equals(shooter)) continue;

            BoundingBox box = living.getBoundingBox().expand(raySize);
            if (box.rayTrace(start, direction, distance) != null) {
                hits.add(living);
            }
        }

        hits.sort(Comparator.comparingDouble(e -> e.getBoundingBox().getCenter().distanceSquared(start)));
        return hits;
    }

    private List<Vector> branch(Vector dir) {
        Vector d = dir.clone().normalize();
        Vector axis = new Vector(0, 1, 0).subtract(d.clone().multiply(d.getY()));
        if (axis.lengthSquared() < 1.0E-6) {
            axis = new Vector(1, 0, 0);
        }
        axis.normalize();
        Vector left  = d.clone().rotateAroundAxis(axis, Math.toRadians(10));
        Vector right = d.clone().rotateAroundAxis(axis, Math.toRadians(-10));
        return List.of(left, right);
    }

    private void drawLine(Player player, Location start,Location end) {

        Vector between = end.toVector().subtract(start.toVector());
        double distance = between.length();
        Vector step = between.normalize().multiply(0.5); // 0.5 = gap between particles

        Location current = start.clone();
        for (double d = 0; d < distance; d += 0.5) {
            player.getWorld().spawnParticle(Particle.DUST, current, 1, 0, 0, 0, 0,particle);
            current.add(step);
        }
    }

}