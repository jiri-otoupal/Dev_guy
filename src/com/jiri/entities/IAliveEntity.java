package com.jiri.entities;

public interface IAliveEntity {
    abstract void die();

    abstract void Jump();

    abstract void JumpVertical();

    abstract void Crouch();

    abstract void MoveLeft();

    abstract void MoveRight();

    abstract void Shoot();
}
