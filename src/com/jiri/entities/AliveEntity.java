package com.jiri.entities;

import com.jiri.level.Level;

import java.awt.*;
import java.awt.geom.Point2D;

public class AliveEntity extends Movable {
    protected float jumpHeight;

    public AliveEntity(Level currentLevel, int health, float speed, float fireRate, float jumpHeight, float gravity) {
        super(currentLevel, true);
        this.health = health;
        this.speed = speed;
        this.fireRate = fireRate;
        this.gravity = gravity;
        this.jumpHeight = jumpHeight;
    }

    public void Jump() {
        float x = this.speed / -2;
        if (!this.facingLeft)
            x = this.speed / 2;
        if (this.falling)
            return;
        for (int i = 0; i < jumpHeight; i++)
            addMovement(x, -1F);

    }

    public void Crouch() {
    }

    public void MoveLeft() {
        this.facingLeft = true;
        addMovement(this.speed * -1, 0);
    }

    public void MoveRight() {
        this.facingLeft = false;
        addMovement(this.speed, 0);
    }

    public void Shoot() {
        if (this.facingLeft) {
            Point muzzlePointLeft = this.muzzlePoints.get(0);
            Projectile mv = new Projectile(this.currentLevel, 5, 1, true, false, new Point(muzzlePointLeft.x - 1, muzzlePointLeft.y), new Point2D.Float(-0.2F, 0F));
        } else {
            Point muzzlePointRight = this.muzzlePoints.get(1);
            Projectile mv = new Projectile(this.currentLevel, 5, 1, true, false, new Point(muzzlePointRight.x + 1, muzzlePointRight.y), new Point2D.Float(0.2F, 0F));
        }
    }
}
