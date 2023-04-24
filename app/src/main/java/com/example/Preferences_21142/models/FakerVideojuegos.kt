package com.example.Preferences_21142.models

import com.example.Preferences_21142.R

class FakerVideojuegos {

    fun getVideogames() : ArrayList<Videojuego>{
        var videogames : ArrayList<Videojuego>
        videogames = arrayListOf<Videojuego>()

        videogames.add(Videojuego(1, "kirbymon Violet", 1300F, "Nintendo Switch",
            R.drawable.kirby, "Everyone"))
        videogames.add(Videojuego(2, "Mario", 1000F, "Nintendo Switch", R.drawable.mario, "Teen"))
        videogames.add(Videojuego(3, "Hades", 100F, "Xbox One", R.drawable.hades, "Teen"))
        videogames.add(Videojuego(4, "The Last of Us II", 500F, "PS4", R.drawable.thelastofus, "Mature"))
        videogames.add(Videojuego(5, "Minecraft", 700F, "PC", R.drawable.minecraft, "Teen"))
        videogames.add(Videojuego(6, "Diablo", 300F, "Xbox One", R.drawable.diablo,"Mature"))
        videogames.add(Videojuego(7, "The withcer 3", 400F, "PS5", R.drawable.witch, "Mature"))
        return videogames
    }

}