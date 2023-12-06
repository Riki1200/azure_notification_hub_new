package com.romeodev.azure_notification_hub_new

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.RemoteMessage
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result


class AzureNotificationHubNewPlugin:  FlutterPlugin, MethodChannel.MethodCallHandler, BroadcastReceiver() {

  private var applicationContext: Context? = null
  private var channel: MethodChannel? = null

  override fun onAttachedToEngine(binding: FlutterPluginBinding) {
    onAttachedToEngine(binding.applicationContext, binding.flutterEngine.dartExecutor)
  }

  override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
    LocalBroadcastManager.getInstance(binding.applicationContext).unregisterReceiver(this)
  }

  private  fun onAttachedToEngine(context: Context, binaryMessenger: BinaryMessenger) {
    this.applicationContext = context
    channel = MethodChannel(binaryMessenger, "azure_notification_hub_new")
    channel!!.setMethodCallHandler(this)
    val intentFilter = IntentFilter()
    intentFilter.addAction(NotificationService.ACTION_TOKEN)
    intentFilter.addAction(NotificationService.ACTION_REMOTE_MESSAGE)
    val manager = LocalBroadcastManager.getInstance(applicationContext!!)
    manager.registerReceiver(this, intentFilter)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "configure") {
      registerWithNotificationHubs()
      NotificationService.createChannelAndHandleNotifications(applicationContext)
    } else {
      result.notImplemented()
    }
  }

  private fun registerWithNotificationHubs() {
    val intent = Intent(applicationContext, RegistrationIntentService::class.java)
    applicationContext!!.startService(intent)
  }

  override fun onReceive(context: Context?, intent: Intent) {
    val action = intent.action ?: return
    if (action == NotificationService.ACTION_TOKEN) {
      val token = intent.getStringExtra(NotificationService.EXTRA_TOKEN)
      channel!!.invokeMethod("onToken", token)
    } else if (action == NotificationService.ACTION_REMOTE_MESSAGE) {
      val message =
        intent.getParcelableExtra<RemoteMessage>(NotificationService.EXTRA_REMOTE_MESSAGE)
      val content = NotificationService.parseRemoteMessage(message)
      channel!!.invokeMethod("onMessage", content)
    }
  }
}