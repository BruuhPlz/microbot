package net.runelite.client.plugins.ogPlugins.ogAgility.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.coords.WorldPoint;

@Getter
@RequiredArgsConstructor
public enum Rooftops {
    SEERS_VILLAGE(new int[] {14927,14928,14932,14929,14930,14931} , new WorldPoint(2727, 3485, 0) , new WorldPoint(2704, 3463, 0), new WorldPoint(2722,3473,0));

    private final int[] obstacleArray;
    private final WorldPoint beginningPoint;
    private final WorldPoint endOFObstaclePoint;
    private final WorldPoint endOFObstacleHalfBackPoint;
}
