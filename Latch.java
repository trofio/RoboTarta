/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  X-Team
 * PROJECT       :  Motor driver using Raspberry Pi & Adafruit Motor
 *                  shield clone with PI4J libraries
 * FILENAME      :  Latch.java
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

public class Latch {

public void Latch() {

  }

// accetta il numero del pin da mandare alto sullo shift register
public void seleziona(int up1, int up2) {

  System.out.println("Seleziona!");
  // definisco le correlazioni tra i PIN del GPIO e il
  // significato logico per lo shift register
  int MOTORLATCH = 0;
  int MOTORCLK = 1;
  int MOTORENABLE = 2;
  int MOTORDATA =  3;

  Gpio.digitalWrite(MOTORLATCH, 0);
  Gpio.digitalWrite(MOTORDATA, 0);

  // creo il latch
  for (int i=0; i<8; i++) {
    Gpio.delay(10);
    Gpio.digitalWrite(MOTORCLK, 0);
    Gpio.delay(10);
    if (i==up1 || i==up2) {
        System.out.println("alzo DATA di " + i);
        Gpio.digitalWrite(MOTORDATA, 1);
      }
    else {
        // System.out.println("abbasso DAT di " + i);
        Gpio.digitalWrite(MOTORDATA, 0);
    }
    Gpio.delay(10);
    Gpio.digitalWrite(MOTORCLK, 1);
  }

  // spedisco il latch creato ai pin di uscita dello shift register
  Gpio.digitalWrite(MOTORLATCH, 1);
  }
}
