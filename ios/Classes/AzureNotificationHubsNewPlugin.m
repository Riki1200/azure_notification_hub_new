#import "Flutter/Flutter.h"
#import "AzureNotificationHubsNewPlugin.h"
#import "WindowsAzureMessaging/WindowsAzureMessaging.h"

@implementation AzureNotificationHubsNewPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAzureNotificationHubsPlugin registerWithRegistrar:registrar];
}
@end
