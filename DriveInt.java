/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  X-Team
 * PROJECT       :  Motor driver using Raspberry Pi & Adafruit Motor
 *                  shield clone with PI4J libraries
 * FILENAME      :  DriveInt.java
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

interface DriveInt {

  public void avanti(int spazio, int velocita);

  public void indietro(int spazio, int velocita);

  public void ruotAdx (int gradi);

  public void ruotAsx (int gradi);

  public void curvAdx();

  public void curvAsx();

  public void stop();

}
