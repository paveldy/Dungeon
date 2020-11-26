package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.Gdx;
import ru.geekbrains.dungeon.helpers.Assets;

public class Hero extends Unit {
    private String name;
    private int count;

    //++++++++++++++++++++++++++
    public int getCount() {
        return count;
    }

    public int getMoney() {
        return money;
    }

    //++++++++++++++++++++++++++


    public Hero(GameController gc) {
        super(gc, 1, 1, 10);
        this.name = "Sir Mullih";
        this.hpMax = 100;
        this.hp = this.hpMax;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        this.count = 0;
    }

    public void update(float dt) {
        super.update(dt);
        if (Gdx.input.justTouched() && canIMakeAction()) {
            Monster m = gc.getUnitController().getMonsterController().getMonsterInCell(gc.getCursorX(), gc.getCursorY());
            if (m != null && canIAttackThisTarget(m)) {
                attack(m);
            } else {
                goTo(gc.getCursorX(), gc.getCursorY());
            }
        }
    }

    public String getName() {
        return name;
    }

    //++++++++++++++++++++++++++

    public void countCalc() {
        count++;
        if (count % 3 == 0) {
            gc.getUnitController().initNewMonster();
        }
    }

    public void money() {
        if (!isStayStill())
            money = money + (int) (Math.random() * 3);
    }

    //+++++++++++++++++++++++++++

}
