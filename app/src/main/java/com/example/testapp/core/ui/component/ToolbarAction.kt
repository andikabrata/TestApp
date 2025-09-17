package com.example.testapp.core.ui.component

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
sealed class ToolbarAction {
    object BackArrow : ToolbarAction()
    object Filter : ToolbarAction()
    object Notification : ToolbarAction()
}