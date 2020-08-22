package me.hufman.androidautoidrive.carapp.notifications


interface ReplyController {
	fun getSuggestions(draft: String): List<CharSequence>
	fun sendReply(reply: String)
}

class ReplyControllerNotification(val notification: CarNotification, val controller: CarNotificationController, val actionTitle: String): ReplyController {
	val action = notification.actions.first { it.title == actionTitle }

	override fun getSuggestions(draft: String): List<CharSequence> {
		return action.remoteInputs.flatMap { remoteInput ->
			remoteInput.choices.asIterable()
		}
	}

	override fun sendReply(reply: String) {
		controller.reply(notification, action.title.toString(), reply)
	}
}