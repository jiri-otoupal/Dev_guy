package com.jiri.entities;

import com.jiri.level.Level;
import com.jiri.structure.ForceVector;

import java.awt.*;
import java.awt.geom.Point2D;

public interface IAliveEntity {
    void die();

    void Jump();

    void JumpVertical();

    void Crouch();

    void MoveLeft();

    void MoveRight();

    void Shoot();

    void spawnProjectile(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, ForceVector vector);

    void sayDialog(String text);

    void sayStatic(String text);
}
