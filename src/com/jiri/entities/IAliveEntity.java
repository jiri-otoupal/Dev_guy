package com.jiri.entities;

public interface IAliveEntity {
    void die();

    void Jump();

    void JumpVertical();

    void Crouch();

    void MoveLeft();

    void MoveRight();

    void Shoot();

    void sayDialog(String text);

    void sayStatic(String text);
}
