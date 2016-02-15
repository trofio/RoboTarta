/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  X-Team
 * PROJECT       :  Motor driver using Raspberry Pi & Adafruit Motor
 *                  shield clone with PI4J libraries
 * FILENAME      :  Drive.java
 *
 * This file is part of the Robotarta project. More information about
 * this project can be found here:  http://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2016 Carlo Eutropio
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

 import com.pi4j.wiringpi.Gpio;
 import com.pi4j.wiringpi.GpioUtil;
 import com.pi4j.wiringpi.SoftPwm;

 public class Drive implements DriveInt {

  // definisco le correlazioni tra i PIN del GPIO e il
	// significato logico per lo shift register
	int MOTORLATCH = 0;
	int MOTORCLK = 1;
	int MOTORENABLE = 2;
	int MOTORDATA =  3;

  // motori di sinistra
	int MOTOR_PWM_SX = 4;
  // motori di destra
	int MOTOR_PWM_DX = 5;
  // creo un oggetto Latch da usare per impartire i comandi di movimento
  Latch vai = new Latch();

		public Drive() {

        // costruttore
        System.out.println("costruttore Drive");

        // setup wiringPi
        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }

            // definisco i pin del GPIO che utilizzo come OUT
            GpioUtil.export(MOTORLATCH, GpioUtil.DIRECTION_OUT);
            Gpio.pinMode(MOTORLATCH, Gpio.OUTPUT);
            GpioUtil.export(MOTORCLK, GpioUtil.DIRECTION_OUT);
            Gpio.pinMode(MOTORCLK, Gpio.OUTPUT);
            GpioUtil.export(MOTORENABLE, GpioUtil.DIRECTION_OUT);
            Gpio.pinMode(MOTORENABLE, Gpio.OUTPUT);
            GpioUtil.export(MOTORDATA, GpioUtil.DIRECTION_OUT);
            Gpio.pinMode(MOTORDATA, Gpio.OUTPUT);

            GpioUtil.export(MOTOR_PWM_SX, GpioUtil.DIRECTION_OUT);
            Gpio.pinMode(MOTOR_PWM_SX, Gpio.OUTPUT);
            GpioUtil.export(MOTOR_PWM_DX, GpioUtil.DIRECTION_OUT);
            Gpio.pinMode(MOTOR_PWM_DX, Gpio.OUTPUT);

            // creo il pin PWM per controllare la velocita' dei motori
            SoftPwm.softPwmCreate(MOTOR_PWM_SX,0,100);
          	SoftPwm.softPwmCreate(MOTOR_PWM_DX,0,100);

            System.out.println("<--Pi4J--> Gpio inizializzata");

            // azzero tutti i registri
            init();
            System.out.println("<--Pi4J--> shift register inizializzato");
        }

    public void init() {

        SoftPwm.softPwmWrite(MOTOR_PWM_SX, 0);
        SoftPwm.softPwmWrite(MOTOR_PWM_DX, 0);

        Gpio.digitalWrite(MOTORLATCH, 0);
        Gpio.digitalWrite(MOTORDATA, 0);

        // creo un LATCH con 8 bit a 0
        for (int i=0; i<8; i++) {
          System.out.println("azzero DATA di:" + i);
          Gpio.delay(10);
          Gpio.digitalWrite(MOTORCLK, 0);
          Gpio.delay(10);
          Gpio.digitalWrite(MOTORDATA, 0);
          Gpio.delay(10);
          Gpio.digitalWrite(MOTORCLK, 1);
        }

        // preparato il LATCH lo invio
        Gpio.digitalWrite(MOTORLATCH, 1);

        // MOTORENABLE LOW serve per abilitare il controllo degli L293D
        Gpio.digitalWrite(MOTORENABLE, 0);

      }

    public void avanti(int spazio, int velocita) {

      // la Robotarta si sposta in avanti di 'spazio' cm
      vai.seleziona(3, 5); // imposto entrambi i motori in senso orario

      // e poi li aziono a vel max
      SoftPwm.softPwmWrite(MOTOR_PWM_SX, velocita);
      SoftPwm.softPwmWrite(MOTOR_PWM_DX, velocita);

      // calcolo il tempo che ci vuole a percorrere lo spazio indicato
      try {
        Thread.sleep(spazio);
      }
      catch (Exception exc) {}
      stop();

    }

    public void indietro(int spazio, int velocita) {

      // la Robotarta si sposta in avanti di 'spazio' cm
      vai.seleziona(4, 6); // imposto entrambi i motori in senso antiorario

      // e poi li aziono a vel max
      SoftPwm.softPwmWrite(MOTOR_PWM_SX, velocita);
      SoftPwm.softPwmWrite(MOTOR_PWM_DX, velocita);

      // calcolo il tempo che ci vuole a percorrere lo spazio indicato
      try {
        Thread.sleep(spazio);
      }
      catch (Exception exc) {}
      stop();

    }

    public void ruotAdx (int gradi) {

      vai.seleziona(4, 5); // imposto il motore sx in senso orario e
                          // quello dx in senso antiorario

      // e poi li aziono a vel max
      SoftPwm.softPwmWrite(MOTOR_PWM_SX, 100);
      SoftPwm.softPwmWrite(MOTOR_PWM_DX, 100);

      // calcolo il tempo che ci vuole a ruotare di 'x' gradi
      try {
        Thread.sleep(1200);
      }
      catch (Exception exc) {}
      stop();

    }

    public void ruotAsx (int gradi) {

      vai.seleziona(3, 6); // imposto il motore sx in senso antiorario e
                          // quello dx in senso orario

      // e poi li aziono a vel max
      SoftPwm.softPwmWrite(MOTOR_PWM_SX, 100);
      SoftPwm.softPwmWrite(MOTOR_PWM_DX, 100);

      // calcolo il tempo che ci vuole a ruotare di 'x' gradi
      try {
        Thread.sleep(1200);
      }
      catch (Exception exc) {}
      stop();

    }

    public void curvAdx() {



    }

    public void curvAsx() {



    }


    public void stop() {

      // stop ai motori creare un metodo apposta
      SoftPwm.softPwmWrite(MOTOR_PWM_SX, 0);
      SoftPwm.softPwmWrite(MOTOR_PWM_DX, 0);

    }


	}
