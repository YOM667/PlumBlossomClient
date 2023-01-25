package me.youm.plumblossom.ui.clickgui.classic.component

/**
 * @author You_M
 */
interface IComponent {
    fun isHovered(x: Int, y: Int, width: Int, height: Int, mouseX: Int, mouseY: Int) =
        mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height
}