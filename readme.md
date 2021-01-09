# StaticLayout

This demo application compares performance of TextView and a thin wrapper of StaticLayout called StaticLayoutView.

Android TextView is complex and might have performance issues if you have a recycler view that are full of long text.
One approach is to use StaticLayout directly. StaticLayout is used by TextView to perform measurement and layout. By using it directly ( wrapped in our StaticLayoutTextView):
   1. We get rid of the unnecessary complexities of the TextView. E.g. our text is only for display, and not for editing.
   2. We moved the measurement time from onMeasure to setText. Text measurement is expensive, doing it in onMeasure would increase the frame rendering time ( which must be finished within 16ms).
   3. Note that the performance gain is achieved by moving measurement out of onMeasure, but it's still in the UI thread. Nevertheless, when you scroll, recycler view will prefetch and bind view holder in advance. When measurement is done during prefetch, we're using CPU wisely and saves rendering time. (https://medium.com/google-developers/recyclerview-prefetch-c2f269075710)
   4. Further improvement if necessary can be achieved by draw the text on an off-screen canvas beforehand. See https://instagram-engineering.com/improving-comment-rendering-on-android-a77d5db3d82e

## Performance test
300 items in a recycler view, and each item shows 50 words. Running test on Galaxy Note 8 by scrolling the list quickly. The following command is used to collect result:
    adb shell dumpsys gfxinfo com.example.staticlayout reset
|   |    TextView    |   StaticLayout    |
| ------------- | ------------------ | ------------- |
|   Plain text  |   Stats since: 2117949985742114ns |   Stats since: 2118031059414167ns |
|   |   Total frames rendered: 141  |   Total frames rendered: 439  |
|   |   Janky frames: 0 (0.00%) |   Janky frames: 0 (0.00%)  |
|   |   50th percentile: 6ms    |   50th percentile: 5ms  |
|   |   90th percentile: 8ms    |   90th percentile: 5ms  |
|   |   95th percentile: 9ms    |   95th percentile: 6ms  |
|   |   99th percentile: 11ms   |   99th percentile: 7ms  |
|   Styled SpannableString  |   Stats since: 2118212107395139ns|   Stats since: 2118267030219441ns  |
|   |   Total frames rendered: 597  |   Total frames rendered: 495  |
|   |   Janky frames: 7 (1.17%) |   Janky frames: 0 (0.00%)  |
|   |   50th percentile: 7ms    |   50th percentile: 6ms  |
|   |   90th percentile: 11ms   |   90th percentile: 9ms  |
|   |   95th percentile: 12ms   |   95th percentile: 9ms  |
|   |   99th percentile: 18ms   |   99th percentile: 11ms  |
