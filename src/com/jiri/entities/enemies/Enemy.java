package com.jiri.entities.enemies;

import com.jiri.entities.AliveEntity;
import com.jiri.level.Level;
import com.jiri.structure.PointExtended;

public class Enemy extends AliveEntity implements IEnemy {
    AI enemyAI;
    public String quest_name;


    public Enemy(Level currentLevel, int health, float speed, long fireRateMs, float jumpHeight, float gravity, int acceptableRadiusToPlayer) {
        super(currentLevel, health, speed, fireRateMs, jumpHeight, gravity);
        this.frameDurationMs = 50;
        this.loops = true;
        this.enemyAI = new AI(this, this.currentLevel.streamer.controller.controlledAliveEntity, acceptableRadiusToPlayer, 300);
    }


    @Override
    public int resolveAnimationState() {
        if (falling) {
            return 1;
        } else if (moving) {
            return 2;
        } else if (shooting) {
            return 3;
        }
        //Idle State
        return 0;
    }

    @Override
    public void die() {
        this.frameDurationMs = 20;
        this.loops = false;
        lifeSpan = 200;
        canGetOlder = true;
        if (currentRenderedText != null)
            currentRenderedText.erase();
        if (currentLevel.quest != null)
            this.currentLevel.quest.markProgress(this.quest_name);
    }

    @Override
    public void attackIfClose() {
        PointExtended side = this.enemyAI.pathToPlayer.get(0).minus(this.enemyAI.pathToPlayer.get(1));
        facingLeft = side.point.equals(Directions.Left.vector);
        Shoot();
    }

    @Override
    public void moveOnPath() {
        int pathSize = this.enemyAI.pathToPlayer.size();
        PointExtended side = new PointExtended(0, 0, null);
        for (int y = 1; y < pathSize && side.point.x == 0; y++) {
            PointExtended tmp = this.enemyAI.pathToPlayer.get(pathSize - (y + 1)).minus(this.enemyAI.pathToPlayer.get(pathSize - y));
            side.point.x += tmp.x();
            side.point.y = tmp.y();
        }
        if (side.point.y == 0)
            side.point.y = this.enemyAI.pathToPlayer.get(pathSize - 3).minus(this.enemyAI.pathToPlayer.get(pathSize - 2)).y() * -1;
        if (side.x() == -1 && side.y() <= 0) {
            if (collisionDirections.contains(Directions.Left.vector))
                Jump();
            MoveLeft();
        } else if (side.x() == 1 && side.y() <= 0) {
            if (collisionDirections.contains(Directions.Right.vector))
                Jump();
            MoveRight();
        } else if (side.y() > 0 && side.x() == 0) {
            JumpVertical();
        } else if (side.y() > 0 && side.x() != 0) {
            Jump();
        }
    }


}
