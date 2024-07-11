package Ejercicios.contorllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import Ejercicios.models.Celda;

/*
 * Un jugador está en la esquina superior izquierda (0,0) de un tablero m x n. En el tablero hay celdas
 * transitables (true) y no transitables (false). Encuentra un camino válido para ir a la esquina
 * inferior izquierda con el jugador, sabiendo que solo se puede mover hacia abajo y hacia la derecha.
 *
 * Ejemplo 1:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,false,false,true]
 *      [true,true,false,true]
 *      [true,true,false,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (0,2), (0,3), (1,3), (2,3), (3,3)]
 *
 * Ejemplo 2:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,true,true,true]
 *      [true,true,false,false]
 *      [true,true,true,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (1,1), (2,1), (3,1), (3,2), (3,3)]
 *
 */
public class Laberinto {

        public List<Celda> getPath(boolean[][] grid) {
            List<Celda> path = new ArrayList<>();
            if(grid== null || grid.length == 0 || grid[0].length == 0){
                return path;
            }
    
            //Map para almacener si ya visitamos una celda y si es parte del camino
            Map<Celda,Boolean> cache = new HashMap<>();
            if(findPath(grid,0,0,path,cache)){
                return path;
            }
            return null;// No retorna si no hay camino posible
        }
    
        //Metodo creado para encontrar el camino
        private boolean findPath(boolean[][] grid, int row, int col, List<Celda> path, Map<Celda, Boolean> cache) {
            //Caso base: si estamos fuera de los limites o la celda no es trnsitable, devolvemos false
            if(row >= grid.length || col >= grid[0].length || !grid [row][col]){
                return false;
            }
            Celda current = new Celda(row, col);
    
            if (cache.containsKey(current)) {
                return cache.get(current);
            }
            //Añadimos la celda actual al camino
            path.add(current);
            //Llegamos a la esquina inferior derecha
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                cache.put(current, true);
                return true;
            }
            //Nos movemos hacia la derecha
            if (findPath(grid, row, col + 1, path, cache)) {
                cache.put(current, true);
                return true;
            }
            //Movemos hacia abajo
            if (findPath(grid, row + 1, col, path, cache)) {
                cache.put(current, true);
                return true;
            }
    
            //Si no encontramos un camino, retrocedemos
            path.remove(path.size() - 1);
            cache.put(current, false);
            return false;
        }
           ;
    }
