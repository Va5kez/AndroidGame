package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Test extends ApplicationAdapter {
	SpriteBatch batch;
    Texture background1;
	Texture character_textures[];
    Texture enemigo;
    Texture game;
    int frame, bag_x, velocidad = 5, enemigo_x, textura_actual;
    double y, velocidad_y, velocidadi=20;
    boolean game_over;
    double gravedad = -1;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        character_textures = new Texture[6];
		character_textures[0] = new Texture("1.png");
        character_textures[1] = new Texture("2.png");
        character_textures[2] = new Texture("3.png");
        character_textures[3] = new Texture("4.png");
        character_textures[4] = new Texture("5.png");
        character_textures[5] = new Texture("6.png");
        background1 = new Texture("wasd.png");
        enemigo = new Texture("spike3.png");
        game = new Texture("gameover.png");
        frame = 0;
        textura_actual = 0;
        game_over = false;
        bag_x =0;
        enemigo_x = 600;

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(background1, bag_x, 0);
        batch.draw(background1, bag_x+630, 0);
		batch.draw(character_textures[textura_actual], 0, (int)y);
        batch.draw(enemigo, enemigo_x, 60);
        if(game_over)
            batch.draw(game, 0, 0);
		batch.end();

        if(frame%20==0){
            textura_actual++;
            if(textura_actual>5)
                textura_actual=0;
        }
        enemigo_x-=velocidad;
        if(enemigo_x<=-640)
            enemigo_x= 640;

        if(enemigo_x<character_textures[textura_actual].getWidth()-10
                && enemigo_x-enemigo.getWidth()>0 && y<100)
        {
            game_over=true;
        }

        if(Gdx.input.justTouched())
        {
            // Velocidad inicial del juego
            velocidad_y=velocidadi;
            game_over=false;
        }
        bag_x-=velocidad;
        if(bag_x<= -640)
            bag_x =0;
        //Aqui se pone la gravedad
        velocidad_y+=gravedad;
        y+=velocidad_y;
        // Esta es la funcion para el piso del juego
        if(y<60)
            y=60;

        frame++;
	}
}
