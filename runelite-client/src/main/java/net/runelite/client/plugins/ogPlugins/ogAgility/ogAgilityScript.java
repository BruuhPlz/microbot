package net.runelite.client.plugins.ogPlugins.ogAgility;

import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.plugins.microbot.util.grounditem.Rs2GroundItem;
import net.runelite.client.plugins.microbot.util.magic.Rs2Magic;
import net.runelite.client.plugins.microbot.util.math.Random;
import net.runelite.client.plugins.microbot.util.tabs.Rs2Tab;
import net.runelite.client.plugins.microbot.util.tabs.Tab;
import net.runelite.client.plugins.microbot.util.widget.Rs2Widget;
import net.runelite.client.plugins.ogPlugins.ogAgility.enums.Rooftops;
import net.runelite.client.plugins.skillcalculator.skills.MagicAction;

import java.util.concurrent.TimeUnit;


public class ogAgilityScript extends Script {

    public static double version = 1.0;
    private enum Status {GO_TO_BEGINNING, DOING_COURSE};
    private Status currentStatus;
    private ogAgilityConfig currentconfig;
    private void callDelay(){
        if(Random.random(1, currentconfig.delayChance()) == 3) {
            int delayGeneratedMin = Random.random(currentconfig.delayMin() - 20, currentconfig.delayMin());
            int delayGeneratedMax = Random.random(currentconfig.delayMax() , currentconfig.delayMax() + 20);
            int delayGenerated = Random.random(delayGeneratedMin,delayGeneratedMax);
            if(currentconfig.verboseLogging()){System.out.println("Delay range of: " + delayGeneratedMin + "-" + delayGeneratedMax + " generated a delay of " + delayGenerated + "ms.");}
            sleep(delayGenerated);
        } else {
            int delayGenerated = Random.random(currentconfig.delayMin(),currentconfig.delayMax());
            if(currentconfig.verboseLogging()){System.out.println("Delay range of: " + currentconfig.delayMin() + "-" + currentconfig.delayMax() + " generated a delay of " + delayGenerated + "ms.");}
            sleep(delayGenerated);
        }
    }
    private void callAFK(){
        if(Random.random(0,currentconfig.afkChance()) == 3) {
            int afkGenerated = Random.random( currentconfig.afkMin() * 600 , currentconfig.afkMax() * 600 );
            if(currentconfig.verboseLogging()){System.out.println("AFK range of: " + currentconfig.afkMin() + "-" + currentconfig.afkMax() + " generated a delay of " + afkGenerated + "minutes.");}
            sleep(afkGenerated);
        }
    }

    private WorldPoint playerLocation () { return Microbot.getClient().getLocalPlayer().getWorldLocation(); }

    private boolean playerNearBeginning () {
        return playerLocation().distanceTo(currentconfig.selectedCourse().getBeginningPoint()) < 7;
    }
    private boolean playerDoingNothing() { return (Microbot.getClient().getLocalPlayer().getAnimation() == -1) && (Microbot.getClient().getLocalPlayer().getPoseAnimation() == 813); }
    public boolean run(ogAgilityConfig config) {
        Microbot.enableAutoRunOn = false;
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            if (!super.run()) return;
            try {
                this.currentconfig = config;
                if(currentconfig.verboseLogging()){System.out.println("Script rerunning");}
                calcState();
                if(currentStatus == Status.GO_TO_BEGINNING) { goToBeginning(); }
                else if(currentStatus == Status.DOING_COURSE) { doCourse(); }


            } catch (Exception ex) {System.out.println(ex.getMessage());}
        }, 0, Random.random(0,300), TimeUnit.MILLISECONDS);
        return true;
    }
    private void calcState() {
        if(playerLocation().getPlane() == 0){ currentStatus = Status.GO_TO_BEGINNING; }
        else { currentStatus = Status.DOING_COURSE; }
    }

    private void goToBeginning() {
        if(currentconfig.selectedCourse() == Rooftops.SEERS_VILLAGE) {
            Rs2Tab.switchToMagicTab();
            callDelay();
            //Rs2Magic.cast(MagicAction.CAMELOT_TELEPORT);
            callDelay();
            sleepUntil(() -> Microbot.getClient().getLocalPlayer().getAnimation() != 714, Random.random(3000,4000));
        }
    }
    private void  doCourse () {
        for(int obstacle : currentconfig.selectedCourse().getObstacleArray()) {
            if(Rs2GroundItem.exists(ItemID.MARK_OF_GRACE, 7)){ Rs2GroundItem.interact(ItemID.MARK_OF_GRACE, "Take", 7); }
            if(Rs2GameObject.findObject(obstacle) != null){ Microbot.getMouse().click(Rs2GameObject.findObjectById(obstacle).getClickbox().getBounds()); sleepUntil(() -> !playerDoingNothing()); callDelay(); sleepUntil(this::playerDoingNothing); return;}
        }
    }






}
