# Azure Notification Hub New

## Overview

`azure_notification_hub_new` is a Flutter package designed to seamlessly integrate with Azure Notification Hub. It simplifies the process of handling push notifications in Flutter applications. With this package, you can efficiently receive and process notifications.

## Features

- Easy integration with Azure Notification Hub.
- Handles different notification states: launch, resume, and receive.
- Capture and manage notification tokens.

## Installation

To use `azure_notification_hub_new`, add the following to your `pubspec.yaml` file:

```yaml
dependencies:
  azure_notification_hub_new: ^1.0.7
```

Then import the package in your code:

```dart
import 'package:azure_notification_hub_new/azure_notification_hub_new.dart';

AzureNotificationHubNew azureNotificationHubNew = AzureNotificationHubNew();

azureNotificationHubNew.configure(
  onLaunch: (Map<String, dynamic> message) async {
    print('onLaunch: $message');
  },
  onResume: (Map<String, dynamic> message) async {
    print('onResume: $message');
  },
  onMessage: (Map<String, dynamic> message) async {
    print('onMessage: $message');
  },
);



```


### Dependency

To use `azure_notification_hub_new` in your Flutter project, add the following dependency to your `pubspec.yaml` file:

```yaml
dependencies:
  azure_notification_hub_new: ^1.0.7