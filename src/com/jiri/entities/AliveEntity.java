package com.jiri.entities;

import com.jiri.entities.textrender.DialogText;
import com.jiri.entities.textrender.StaticText;
import com.jiri.level.Level;
import com.jiri.projectile.Projectile;
import com.jiri.structure.ForceVector;

import java.awt.*;
import java.util.Random;

public class AliveEntity extends Animated implements IAliveEntity {
    protected boolean crouching = false;
    protected float jumpHeight;
    protected boolean shooting = false;
    protected String[] damageReactions;
    public boolean dead = false;


    public AliveEntity(Level currentLevel, int health, float speed, long fireRate, float jumpHeight, float gravity) {
        super(currentLevel, true);
        this.health = health;
        this.speed = speed;
        this.fireRate = fireRate;
        this.gravity = gravity;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void JumpVertical() {
        float x = 0;
        if (this.falling)
            return;
        for (int i = 0; i < jumpHeight; i++)
            addMovement(x, -1F);
    }

    @Override
    public void Jump() {
        if (this.crouching) {
            this.crouching = false;
            return;
        }
        float x = 0;
        if (this.facingLeft && !this.collisionDirections.containsKey(Directions.Left.vector)) {
            x = this.speed / -2;
        } else if (!this.facingLeft && !this.collisionDirections.containsKey(Directions.Right.vector)) {
            x = this.speed / 2;
        }

        if (this.falling)
            return;
        for (int i = 0; i < jumpHeight; i++)
            addMovement(x, -1F);
    }

    @Override
    public void Crouch() {
        this.crouching = true;
    }

    @Override
    public void MoveLeft() {
        this.facingLeft = true;
        addMovement(this.speed * -1, 0);
    }

    @Override
    public void MoveRight() {
        this.facingLeft = false;
        addMovement(this.speed, 0);
    }

    @Override
    public void Shoot() {
        if (timeToShoot <= 0 && this.muzzlePoints.size() > 0) {
            Point muzzlePoint;
            ForceVector vector;
            //this.shooting = true;
            this.timeToShoot = fireRate;
            if (this.facingLeft) {
                Point muzzlePointLeft = this.muzzlePoints.get(0);
                muzzlePoint = new Point(muzzlePointLeft.x - 1, muzzlePointLeft.y);
                vector = new ForceVector(-0.2F, 0F);
            } else {
                Point muzzlePointRight = this.muzzlePoints.get(1);
                muzzlePoint = new Point(muzzlePointRight.x + 1, muzzlePointRight.y);
                vector = new ForceVector(0.2F, 0F);
            }
            Entity1D projectileSpawnPoint = this.currentLevel.streamer.getInstanceAt(muzzlePoint);
            if (!projectileSpawnPoint.persistent && !projectileSpawnPoint.canCollide())
                spawnProjectile(this.currentLevel, 5, 1, true, true, muzzlePoint, vector);
            else if (!projectileSpawnPoint.persistent && projectileSpawnPoint.canCollide())
                projectileSpawnPoint.shadow_parent.applyDamage(5);
        }
    }

    public void spawnProjectile(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, ForceVector vector) {
        new Projectile(currentLevel, damage, mass, enableGravity, applyPhysicsImpulse, spawnPoint, vector);
    }

    @Override
    public void sayDialog(String text) {
        new DialogText(currentLevel, text, false, 100, 300, this).spawn(this.textRenderPoint);
    }

    @Override
    public void sayStatic(String text) {
        new StaticText(currentLevel, text, 100).spawnAtPlayer();
    }


    @Override
    public boolean applyDamage(float damage) {
        this.health -= damage;
        if (this.health <= 0 && !dead) {
            this.dead = true;
            die();
        } else if (!dead) {
            if (damageReactions == null || damageReactions.length == 0)
                return true;
            int index = new Random().nextInt( damageReactions.length);
            if (index == -1)
                return true;
            String usedWord = damageReactions[index];
            sayDialog(usedWord);
        }
        return true;
    }

    @Override
    public void die() {
    }

}
