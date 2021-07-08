package com.example.cookbook.recipes_activity.recipes_fragment

import com.example.cookbook.recipes_activity.Ingredient
import com.example.cookbook.recipes_activity.Recipe

object RecipesDatabase {

    val recipes: MutableList<Recipe> = mutableListOf(
        Recipe("Kurczak terayaki", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", mutableListOf(
            Ingredient("składnik 1"), Ingredient("składnik 2"), Ingredient("składnik 3")
        )),
        Recipe("Pizza z ananasem", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In porta enim sed mi vulputate, pharetra congue nulla vehicula. Sed malesuada mauris ac enim euismod, quis aliquam tortor feugiat. Curabitur gravida risus erat, at tempus tortor molestie in. Sed tristique elit urna, non gravida urna placerat quis. Cras vitae massa pharetra, dapibus ipsum sed, tempor neque. Aliquam id massa finibus, viverra nibh sed, pellentesque lectus. Nunc nec purus ac risus semper ullamcorper et ac metus. Quisque elementum molestie nisl elementum sagittis. Maecenas nulla felis, fringilla at lacus sit amet, dictum facilisis turpis. Cras rutrum venenatis dui, quis venenatis erat bibendum consequat. Nunc vitae vestibulum tortor, in faucibus leo. Pellentesque sodales lacus nec ipsum vehicula, eget placerat leo rutrum. Nulla in rutrum orci.", mutableListOf(
            Ingredient("składnik 1"), Ingredient("składnik 2"), Ingredient("składnik 3")
        )),
        Recipe("Makaron z serem", "Nam ac purus augue. Etiam sed risus ac velit faucibus finibus. In porta luctus risus eu venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent id feugiat nulla, ut consectetur lectus. Fusce sed urna ut justo ornare luctus quis fringilla lorem. Vivamus sed purus sit amet nibh hendrerit tristique a quis metus. Nam vulputate dignissim posuere. Duis neque odio, imperdiet pellentesque leo nec, sollicitudin aliquet elit. Aenean nisl arcu, egestas vitae odio eget, commodo viverra sem. Nulla egestas nulla at nunc posuere placerat. Nunc maximus velit eros, vel tincidunt quam aliquam vel.", mutableListOf(
            Ingredient("składnik 1"), Ingredient("składnik 2"), Ingredient("składnik 3")
        )),
        Recipe("Naleśniki", "Nunc convallis consequat velit non pharetra. Fusce convallis ipsum ultrices quam scelerisque pulvinar. Morbi a nisi sagittis odio dignissim mattis a at tellus. Curabitur turpis orci, tempus venenatis tellus vel, blandit accumsan metus. Mauris tincidunt urna vel diam pellentesque maximus eget aliquet erat. Aenean libero nisl, viverra at ex non, tempus bibendum dolor. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque fermentum, odio id ultrices lobortis, eros sapien malesuada urna, ut tempor lectus sapien at leo. Sed malesuada velit id eleifend dictum. Aliquam vitae risus at ante lacinia rutrum. Mauris orci tellus, hendrerit quis consequat in, lacinia nec massa. Integer eu enim sed risus cursus aliquet id a sem.", mutableListOf(
            Ingredient("składnik 1"), Ingredient("składnik 2"), Ingredient("składnik 3")
        )),
        Recipe("Placki ziemniaczane", "Suspendisse nec condimentum est. Pellentesque vel vulputate quam. Pellentesque sagittis ex at quam commodo vehicula. Duis ac nisi lorem. Nunc maximus pharetra commodo. Fusce viverra arcu velit, tristique mattis mi condimentum ac. Nulla ut viverra mauris. Sed cursus magna velit, eget luctus lacus vulputate in. Ut ac nisi at elit porttitor mattis. Aliquam laoreet nisl eu enim venenatis ullamcorper. Cras tempor nec dolor sed commodo. Suspendisse efficitur lobortis justo, vitae mattis libero egestas vel. Cras eget pulvinar ipsum. Donec in consectetur eros.", mutableListOf(
            Ingredient("składnik 1"), Ingredient("składnik 2"), Ingredient("składnik 3")
        ))
    )
}