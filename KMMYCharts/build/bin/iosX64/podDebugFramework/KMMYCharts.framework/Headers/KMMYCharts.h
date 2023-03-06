#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class KMMYCAxisConfig, KMMYCGravity, KMMYCDataCategoryOptions, KMMYCAxisData, KMMYCAxisDataBuilder, KMMYCKotlinEnumCompanion, KMMYCKotlinEnum<E>, KMMYCKotlinArray<T>, KMMYCAccessibilityConfig, KMMYCLegendLabel, KMMYCUi_textTextStyle, KMMYCLegendsConfig, KMMYCPlotTypeBar, KMMYCPlotTypeDonut, KMMYCPlotTypeLine, KMMYCPlotTypePie, KMMYCPlotTypeWave, KMMYCPoint, KMMYCChartConstants, KMMYCLineStyle, KMMYCIntersectionPoint, KMMYCSelectionHighlightPoint, KMMYCShadowUnderLine, KMMYCSelectionHighlightPopUp, KMMYCWave, KMMYCWavePlotData, KMMYCGridLines, KMMYCWaveChartData, KMMYCWavePlotDataCompanion, KMMYCUi_graphicsColorFilter, KMMYCUi_graphicsDrawStyle, KMMYCLine, KMMYCLinePlotData, KMMYCLineChartData, KMMYCLinePlotDataCompanion, KMMYCLineType, KMMYCKotlinFloatArray, KMMYCLineTypeSmoothCurve, KMMYCLineTypeStraight, KMMYCUi_textTextMeasurer, KMMYCUi_graphicsBrush, UIViewController, KMMYCKotlinPair<__covariant A, __covariant B>, KMMYCKotlinTriple<__covariant A, __covariant B, __covariant C>, KMMYCUi_geometryRect, KMMYCUi_textFontWeight, KMMYCUi_textFontFamily, KMMYCUi_textTextGeometricTransform, KMMYCUi_textLocaleList, KMMYCUi_textTextDecoration, KMMYCUi_graphicsShadow, KMMYCUi_textTextIndent, KMMYCUi_textPlatformTextStyle, KMMYCUi_textLineHeightStyle, KMMYCUi_textLineBreak, KMMYCUi_textHyphens, KMMYCUi_textTextStyleCompanion, KMMYCUi_textParagraphStyle, KMMYCUi_textSpanStyle, KMMYCKotlinIntArray, KMMYCUi_unitLayoutDirection, KMMYCUi_graphicsColorFilterCompanion, KMMYCUi_unitDpRect, KMMYCKotlinFloatIterator, KMMYCUi_textTextLayoutResult, KMMYCUi_textAnnotatedString, KMMYCUi_textPlaceholder, KMMYCUi_textAnnotatedStringRange<T>, KMMYCUi_graphicsBrushCompanion, KMMYCUi_geometryRoundRect, KMMYCUi_geometryRectCompanion, KMMYCSkikoPaint, KMMYCSkikoShader, KMMYCUi_textFontWeightCompanion, KMMYCUi_textFontFamilyCompanion, KMMYCUi_textTextGeometricTransformCompanion, KMMYCUi_textLocale, KMMYCUi_textLocaleListCompanion, KMMYCUi_textTextDecorationCompanion, KMMYCUi_graphicsShadowCompanion, KMMYCUi_textTextIndentCompanion, KMMYCUi_textPlatformSpanStyle, KMMYCUi_textPlatformParagraphStyle, KMMYCUi_textLineHeightStyleCompanion, KMMYCUi_textLineBreakCompanion, KMMYCUi_textHyphensCompanion, KMMYCKotlinIntIterator, KMMYCUi_graphicsColorSpace, KMMYCUi_unitDpRectCompanion, KMMYCUi_textTextLayoutInput, KMMYCUi_textMultiParagraph, KMMYCUi_textResolvedTextDirection, KMMYCUi_textTtsAnnotation, KMMYCUi_textUrlAnnotation, KMMYCUi_geometryRoundRectCompanion, KMMYCSkikoNativeCompanion, KMMYCSkikoNative, KMMYCSkikoManaged, KMMYCSkikoPaintCompanion, KMMYCSkikoPath, KMMYCSkikoRect, KMMYCSkikoColor4f, KMMYCSkikoColorSpace, KMMYCSkikoBlendMode, KMMYCSkikoColorFilter, KMMYCSkikoImageFilter, KMMYCSkikoMaskFilter, KMMYCSkikoPaintMode, KMMYCSkikoPathEffect, KMMYCSkikoPaintStrokeCap, KMMYCSkikoPaintStrokeJoin, KMMYCSkikoRefCnt, KMMYCSkikoShaderCompanion, KMMYCUi_textGenericFontFamily, KMMYCUi_textSystemFontFamily, KMMYCUi_textLocaleCompanion, KMMYCUi_textPlatformSpanStyleCompanion, KMMYCUi_textPlatformParagraphStyleCompanion, KMMYCUi_graphicsVertices, KMMYCKotlinThrowable, KMMYCKotlinException, KMMYCKotlinRuntimeException, KMMYCKotlinIllegalStateException, KMMYCUi_textMultiParagraphIntrinsics, KMMYCSkikoPathCompanion, KMMYCSkikoPathDirection, KMMYCSkikoMatrix33, KMMYCSkikoPoint, KMMYCSkikoRRect, KMMYCSkikoPathEllipseArc, KMMYCSkikoPathVerb, KMMYCSkikoPathSegmentIterator, KMMYCKotlinByteArray, KMMYCSkikoPathFillMode, KMMYCSkikoRectCompanion, KMMYCSkikoIRect, KMMYCSkikoColor4fCompanion, KMMYCSkikoColorSpaceCompanion, KMMYCSkikoColorFilterCompanion, KMMYCSkikoImageFilterCompanion, KMMYCSkikoMaskFilterCompanion, KMMYCSkikoPathEffectCompanion, KMMYCSkikoISize, KMMYCSkikoGradientStyle, KMMYCKotlinShortArray, KMMYCSkikoPathOp, KMMYCSkikoMatrix33Companion, KMMYCSkikoMatrix44, KMMYCSkikoPointCompanion, KMMYCSkikoRRectCompanion, KMMYCSkikoPathSegmentIteratorCompanion, KMMYCSkikoPathSegment, KMMYCKotlinByteIterator, KMMYCSkikoIRectCompanion, KMMYCSkikoIPoint, KMMYCSkikoColorMatrix, KMMYCSkikoInversionMode, KMMYCSkikoRegion, KMMYCSkikoFilterTileMode, KMMYCSkikoColorChannel, KMMYCSkikoImage, KMMYCSkikoRuntimeShaderBuilder, KMMYCSkikoFilterBlurMode, KMMYCSkikoPathEffectStyle, KMMYCSkikoISizeCompanion, KMMYCSkikoGradientStyleCompanion, KMMYCKotlinShortIterator, KMMYCSkikoMatrix44Companion, KMMYCSkikoIPointCompanion, KMMYCSkikoRegionCompanion, KMMYCSkikoRegionOp, KMMYCSkikoColorAlphaType, KMMYCSkikoColorInfo, KMMYCSkikoColorType, KMMYCSkikoImageInfo, KMMYCSkikoImageCompanion, KMMYCSkikoData, KMMYCSkikoEncodedImageFormat, KMMYCSkikoPixmap, KMMYCSkikoBitmap, KMMYCSkikoDirectContext, KMMYCSkikoRuntimeEffect, KMMYCSkikoRuntimeShaderBuilderCompanion, KMMYCSkikoMatrix22, KMMYCSkikoRegionOpCompanion, KMMYCSkikoColorInfoCompanion, KMMYCSkikoColorTypeCompanion, KMMYCSkikoImageInfoCompanion, KMMYCSkikoDataCompanion, KMMYCSkikoPixmapCompanion, KMMYCSkikoBitmapCompanion, KMMYCSkikoPixelRef, KMMYCSkikoDirectContextCompanion, KMMYCSkikoGLBackendState, KMMYCSkikoRuntimeEffectCompanion, KMMYCSkikoMatrix22Companion, KMMYCSkikoPixelRefCompanion;

@protocol KMMYCKotlinComparable, KMMYCPlatform, KMMYCFoundation_layoutArrangementHorizontal, KMMYCPlotType, KMMYCPlotData, KMMYCUi_graphicsPathEffect, KMMYCUi_graphicsDrawScope, KMMYCUi_graphicsPath, KMMYCUi_graphicsPaint, KMMYCKotlinIterator, KMMYCUi_unitDensity, KMMYCUi_graphicsImageBitmap, KMMYCUi_graphicsDrawContext, KMMYCUi_textFontFamilyResolver, KMMYCKotlinIterable, KMMYCKotlinCollection, KMMYCUi_graphicsCanvas, KMMYCUi_graphicsDrawTransform, KMMYCRuntimeState, KMMYCKotlinCharSequence, KMMYCUi_textFontResourceLoader, KMMYCUi_textFont, KMMYCUi_textParagraphIntrinsics, KMMYCKotlinMutableIterator, KMMYCSkikoSamplingMode, KMMYCSkikoIHasImageInfo;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface KMMYCBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface KMMYCBase (KMMYCBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface KMMYCMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface KMMYCMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorKMMYCKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface KMMYCNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface KMMYCByte : KMMYCNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface KMMYCUByte : KMMYCNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface KMMYCShort : KMMYCNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface KMMYCUShort : KMMYCNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface KMMYCInt : KMMYCNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface KMMYCUInt : KMMYCNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface KMMYCLong : KMMYCNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface KMMYCULong : KMMYCNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface KMMYCFloat : KMMYCNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface KMMYCDouble : KMMYCNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface KMMYCBoolean : KMMYCNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AxisConfig")))
@interface KMMYCAxisConfig : KMMYCBase
- (instancetype)initWithIsAxisLineRequired:(BOOL)isAxisLineRequired shouldEllipsizeAxisLabel:(BOOL)shouldEllipsizeAxisLabel minTextWidthToEllipsize:(float)minTextWidthToEllipsize ellipsizeAt:(NSString *)ellipsizeAt __attribute__((swift_name("init(isAxisLineRequired:shouldEllipsizeAxisLabel:minTextWidthToEllipsize:ellipsizeAt:)"))) __attribute__((objc_designated_initializer));
- (KMMYCAxisConfig *)doCopyIsAxisLineRequired:(BOOL)isAxisLineRequired shouldEllipsizeAxisLabel:(BOOL)shouldEllipsizeAxisLabel minTextWidthToEllipsize:(float)minTextWidthToEllipsize ellipsizeAt:(NSString *)ellipsizeAt __attribute__((swift_name("doCopy(isAxisLineRequired:shouldEllipsizeAxisLabel:minTextWidthToEllipsize:ellipsizeAt:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *ellipsizeAt __attribute__((swift_name("ellipsizeAt")));
@property (readonly) BOOL isAxisLineRequired __attribute__((swift_name("isAxisLineRequired")));
@property (readonly) float minTextWidthToEllipsize __attribute__((swift_name("minTextWidthToEllipsize")));
@property (readonly) BOOL shouldEllipsizeAxisLabel __attribute__((swift_name("shouldEllipsizeAxisLabel")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AxisData")))
@interface KMMYCAxisData : KMMYCBase
- (instancetype)initWithSteps:(int32_t)steps labelData:(NSString *(^)(KMMYCInt *))labelData axisPos:(KMMYCGravity *)axisPos labelAndAxisLinePadding:(float)labelAndAxisLinePadding axisOffset:(float)axisOffset axisTopPadding:(float)axisTopPadding axisBottomPadding:(float)axisBottomPadding axisStartPadding:(float)axisStartPadding axisEndPadding:(float)axisEndPadding axisStepSize:(float)axisStepSize axisLabelAngle:(float)axisLabelAngle axisLineColor:(uint64_t)axisLineColor axisLabelColor:(uint64_t)axisLabelColor axisLabelFontSize:(int64_t)axisLabelFontSize axisLineThickness:(float)axisLineThickness indicatorLineWidth:(float)indicatorLineWidth backgroundColor:(uint64_t)backgroundColor axisConfig:(KMMYCAxisConfig *)axisConfig startDrawPadding:(float)startDrawPadding shouldDrawAxisLineTillEnd:(BOOL)shouldDrawAxisLineTillEnd axisLabelDescription:(NSString *(^)(NSString *))axisLabelDescription dataCategoryOptions:(KMMYCDataCategoryOptions *)dataCategoryOptions __attribute__((swift_name("init(steps:labelData:axisPos:labelAndAxisLinePadding:axisOffset:axisTopPadding:axisBottomPadding:axisStartPadding:axisEndPadding:axisStepSize:axisLabelAngle:axisLineColor:axisLabelColor:axisLabelFontSize:axisLineThickness:indicatorLineWidth:backgroundColor:axisConfig:startDrawPadding:shouldDrawAxisLineTillEnd:axisLabelDescription:dataCategoryOptions:)"))) __attribute__((objc_designated_initializer));
- (KMMYCAxisData *)doCopySteps:(int32_t)steps labelData:(NSString *(^)(KMMYCInt *))labelData axisPos:(KMMYCGravity *)axisPos labelAndAxisLinePadding:(float)labelAndAxisLinePadding axisOffset:(float)axisOffset axisTopPadding:(float)axisTopPadding axisBottomPadding:(float)axisBottomPadding axisStartPadding:(float)axisStartPadding axisEndPadding:(float)axisEndPadding axisStepSize:(float)axisStepSize axisLabelAngle:(float)axisLabelAngle axisLineColor:(uint64_t)axisLineColor axisLabelColor:(uint64_t)axisLabelColor axisLabelFontSize:(int64_t)axisLabelFontSize axisLineThickness:(float)axisLineThickness indicatorLineWidth:(float)indicatorLineWidth backgroundColor:(uint64_t)backgroundColor axisConfig:(KMMYCAxisConfig *)axisConfig startDrawPadding:(float)startDrawPadding shouldDrawAxisLineTillEnd:(BOOL)shouldDrawAxisLineTillEnd axisLabelDescription:(NSString *(^)(NSString *))axisLabelDescription dataCategoryOptions:(KMMYCDataCategoryOptions *)dataCategoryOptions __attribute__((swift_name("doCopy(steps:labelData:axisPos:labelAndAxisLinePadding:axisOffset:axisTopPadding:axisBottomPadding:axisStartPadding:axisEndPadding:axisStepSize:axisLabelAngle:axisLineColor:axisLabelColor:axisLabelFontSize:axisLineThickness:indicatorLineWidth:backgroundColor:axisConfig:startDrawPadding:shouldDrawAxisLineTillEnd:axisLabelDescription:dataCategoryOptions:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float axisBottomPadding __attribute__((swift_name("axisBottomPadding")));
@property (readonly) KMMYCAxisConfig *axisConfig __attribute__((swift_name("axisConfig")));
@property (readonly) float axisEndPadding __attribute__((swift_name("axisEndPadding")));
@property (readonly) float axisLabelAngle __attribute__((swift_name("axisLabelAngle")));
@property (readonly) uint64_t axisLabelColor __attribute__((swift_name("axisLabelColor")));
@property (readonly) NSString *(^axisLabelDescription)(NSString *) __attribute__((swift_name("axisLabelDescription")));
@property (readonly) int64_t axisLabelFontSize __attribute__((swift_name("axisLabelFontSize")));
@property (readonly) uint64_t axisLineColor __attribute__((swift_name("axisLineColor")));
@property (readonly) float axisLineThickness __attribute__((swift_name("axisLineThickness")));
@property (readonly) float axisOffset __attribute__((swift_name("axisOffset")));
@property (readonly) KMMYCGravity *axisPos __attribute__((swift_name("axisPos")));
@property (readonly) float axisStartPadding __attribute__((swift_name("axisStartPadding")));
@property (readonly) float axisStepSize __attribute__((swift_name("axisStepSize")));
@property (readonly) float axisTopPadding __attribute__((swift_name("axisTopPadding")));
@property (readonly) uint64_t backgroundColor __attribute__((swift_name("backgroundColor")));
@property (readonly) KMMYCDataCategoryOptions *dataCategoryOptions __attribute__((swift_name("dataCategoryOptions")));
@property (readonly) float indicatorLineWidth __attribute__((swift_name("indicatorLineWidth")));
@property (readonly) float labelAndAxisLinePadding __attribute__((swift_name("labelAndAxisLinePadding")));
@property (readonly) NSString *(^labelData)(KMMYCInt *) __attribute__((swift_name("labelData")));
@property (readonly) BOOL shouldDrawAxisLineTillEnd __attribute__((swift_name("shouldDrawAxisLineTillEnd")));
@property (readonly) float startDrawPadding __attribute__((swift_name("startDrawPadding")));
@property (readonly) int32_t steps __attribute__((swift_name("steps")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AxisData.Builder")))
@interface KMMYCAxisDataBuilder : KMMYCBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (KMMYCAxisDataBuilder *)axisConfigConfig:(KMMYCAxisConfig *)config __attribute__((swift_name("axisConfig(config:)")));
- (KMMYCAxisDataBuilder *)axisLabelAngleAngle:(float)angle __attribute__((swift_name("axisLabelAngle(angle:)")));
- (KMMYCAxisDataBuilder *)axisLabelColorColor:(uint64_t)color __attribute__((swift_name("axisLabelColor(color:)")));
- (KMMYCAxisDataBuilder *)axisLabelDescriptionDescription:(NSString *(^)(NSString *))description __attribute__((swift_name("axisLabelDescription(description:)")));
- (KMMYCAxisDataBuilder *)axisLabelFontSizeFontSize:(int64_t)fontSize __attribute__((swift_name("axisLabelFontSize(fontSize:)")));
- (KMMYCAxisDataBuilder *)axisLineColorLineColor:(uint64_t)lineColor __attribute__((swift_name("axisLineColor(lineColor:)")));
- (KMMYCAxisDataBuilder *)axisLineThicknessThickness:(float)thickness __attribute__((swift_name("axisLineThickness(thickness:)")));
- (KMMYCAxisDataBuilder *)axisOffsetOffset:(float)offset __attribute__((swift_name("axisOffset(offset:)")));
- (KMMYCAxisDataBuilder *)axisPositionPos:(KMMYCGravity *)pos __attribute__((swift_name("axisPosition(pos:)")));
- (KMMYCAxisDataBuilder *)axisStepSizeSize:(float)size __attribute__((swift_name("axisStepSize(size:)")));
- (KMMYCAxisDataBuilder *)backgroundColorColor:(uint64_t)color __attribute__((swift_name("backgroundColor(color:)")));
- (KMMYCAxisDataBuilder *)bottomPaddingPadding:(float)padding __attribute__((swift_name("bottomPadding(padding:)")));
- (KMMYCAxisData *)build __attribute__((swift_name("build()")));
- (KMMYCAxisDataBuilder *)endPaddingPadding:(float)padding __attribute__((swift_name("endPadding(padding:)")));
- (KMMYCAxisDataBuilder *)indicatorLineWidthLineWidth:(float)lineWidth __attribute__((swift_name("indicatorLineWidth(lineWidth:)")));
- (KMMYCAxisDataBuilder *)labelAndAxisLinePaddingPadding:(float)padding __attribute__((swift_name("labelAndAxisLinePadding(padding:)")));
- (KMMYCAxisDataBuilder *)labelDataLabelData:(NSString *(^)(KMMYCInt *))labelData __attribute__((swift_name("labelData(labelData:)")));
- (KMMYCAxisDataBuilder *)setDataCategoryOptionsDataCategoryOptions:(KMMYCDataCategoryOptions *)dataCategoryOptions __attribute__((swift_name("setDataCategoryOptions(dataCategoryOptions:)")));
- (KMMYCAxisDataBuilder *)shouldDrawAxisLineTillEndFlag:(BOOL)flag __attribute__((swift_name("shouldDrawAxisLineTillEnd(flag:)")));
- (KMMYCAxisDataBuilder *)startDrawPaddingPadding:(float)padding __attribute__((swift_name("startDrawPadding(padding:)")));
- (KMMYCAxisDataBuilder *)startPaddingPadding:(float)padding __attribute__((swift_name("startPadding(padding:)")));
- (KMMYCAxisDataBuilder *)stepsCount:(int32_t)count __attribute__((swift_name("steps(count:)")));
- (KMMYCAxisDataBuilder *)topPaddingPadding:(float)padding __attribute__((swift_name("topPadding(padding:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DataCategoryOptions")))
@interface KMMYCDataCategoryOptions : KMMYCBase
- (instancetype)initWithIsDataCategoryInYAxis:(BOOL)isDataCategoryInYAxis isDataCategoryStartFromBottom:(BOOL)isDataCategoryStartFromBottom __attribute__((swift_name("init(isDataCategoryInYAxis:isDataCategoryStartFromBottom:)"))) __attribute__((objc_designated_initializer));
- (KMMYCDataCategoryOptions *)doCopyIsDataCategoryInYAxis:(BOOL)isDataCategoryInYAxis isDataCategoryStartFromBottom:(BOOL)isDataCategoryStartFromBottom __attribute__((swift_name("doCopy(isDataCategoryInYAxis:isDataCategoryStartFromBottom:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isDataCategoryInYAxis __attribute__((swift_name("isDataCategoryInYAxis")));
@property (readonly) BOOL isDataCategoryStartFromBottom __attribute__((swift_name("isDataCategoryStartFromBottom")));
@end

__attribute__((swift_name("KotlinComparable")))
@protocol KMMYCKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end

__attribute__((swift_name("KotlinEnum")))
@interface KMMYCKotlinEnum<E> : KMMYCBase <KMMYCKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Gravity")))
@interface KMMYCGravity : KMMYCKotlinEnum<KMMYCGravity *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCGravity *top __attribute__((swift_name("top")));
@property (class, readonly) KMMYCGravity *left __attribute__((swift_name("left")));
@property (class, readonly) KMMYCGravity *right __attribute__((swift_name("right")));
@property (class, readonly) KMMYCGravity *bottom __attribute__((swift_name("bottom")));
+ (KMMYCKotlinArray<KMMYCGravity *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Greeting")))
@interface KMMYCGreeting : KMMYCBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)greet __attribute__((swift_name("greet()")));
@end

__attribute__((swift_name("Platform")))
@protocol KMMYCPlatform
@required
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IOSPlatform")))
@interface KMMYCIOSPlatform : KMMYCBase <KMMYCPlatform>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AccessibilityConfig")))
@interface KMMYCAccessibilityConfig : KMMYCBase
- (instancetype)initWithChartDescription:(NSString *)chartDescription shouldHandleBackWhenTalkBackPopUpShown:(BOOL)shouldHandleBackWhenTalkBackPopUpShown popUpTopRightButtonTitle:(NSString *)popUpTopRightButtonTitle popUpTopRightButtonDescription:(NSString *)popUpTopRightButtonDescription dividerColor:(uint64_t)dividerColor dividerThickness:(float)dividerThickness __attribute__((swift_name("init(chartDescription:shouldHandleBackWhenTalkBackPopUpShown:popUpTopRightButtonTitle:popUpTopRightButtonDescription:dividerColor:dividerThickness:)"))) __attribute__((objc_designated_initializer));
- (KMMYCAccessibilityConfig *)doCopyChartDescription:(NSString *)chartDescription shouldHandleBackWhenTalkBackPopUpShown:(BOOL)shouldHandleBackWhenTalkBackPopUpShown popUpTopRightButtonTitle:(NSString *)popUpTopRightButtonTitle popUpTopRightButtonDescription:(NSString *)popUpTopRightButtonDescription dividerColor:(uint64_t)dividerColor dividerThickness:(float)dividerThickness __attribute__((swift_name("doCopy(chartDescription:shouldHandleBackWhenTalkBackPopUpShown:popUpTopRightButtonTitle:popUpTopRightButtonDescription:dividerColor:dividerThickness:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *chartDescription __attribute__((swift_name("chartDescription")));
@property (readonly) uint64_t dividerColor __attribute__((swift_name("dividerColor")));
@property (readonly) float dividerThickness __attribute__((swift_name("dividerThickness")));
@property (readonly) NSString *popUpTopRightButtonDescription __attribute__((swift_name("popUpTopRightButtonDescription")));
@property (readonly) NSString *popUpTopRightButtonTitle __attribute__((swift_name("popUpTopRightButtonTitle")));
@property (readonly) BOOL shouldHandleBackWhenTalkBackPopUpShown __attribute__((swift_name("shouldHandleBackWhenTalkBackPopUpShown")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LegendLabel")))
@interface KMMYCLegendLabel : KMMYCBase
- (instancetype)initWithColor:(uint64_t)color name:(NSString *)name __attribute__((swift_name("init(color:name:)"))) __attribute__((objc_designated_initializer));
- (KMMYCLegendLabel *)doCopyColor:(uint64_t)color name:(NSString *)name __attribute__((swift_name("doCopy(color:name:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LegendsConfig")))
@interface KMMYCLegendsConfig : KMMYCBase
- (instancetype)initWithLegendLabelList:(NSArray<KMMYCLegendLabel *> *)legendLabelList gridColumnCount:(int32_t)gridColumnCount gridPaddingHorizontal:(float)gridPaddingHorizontal gridPaddingVertical:(float)gridPaddingVertical colorBoxSize:(float)colorBoxSize textStyle:(KMMYCUi_textTextStyle *)textStyle spaceBWLabelAndColorBox:(float)spaceBWLabelAndColorBox legendsArrangement:(id<KMMYCFoundation_layoutArrangementHorizontal>)legendsArrangement __attribute__((swift_name("init(legendLabelList:gridColumnCount:gridPaddingHorizontal:gridPaddingVertical:colorBoxSize:textStyle:spaceBWLabelAndColorBox:legendsArrangement:)"))) __attribute__((objc_designated_initializer));
- (KMMYCLegendsConfig *)doCopyLegendLabelList:(NSArray<KMMYCLegendLabel *> *)legendLabelList gridColumnCount:(int32_t)gridColumnCount gridPaddingHorizontal:(float)gridPaddingHorizontal gridPaddingVertical:(float)gridPaddingVertical colorBoxSize:(float)colorBoxSize textStyle:(KMMYCUi_textTextStyle *)textStyle spaceBWLabelAndColorBox:(float)spaceBWLabelAndColorBox legendsArrangement:(id<KMMYCFoundation_layoutArrangementHorizontal>)legendsArrangement __attribute__((swift_name("doCopy(legendLabelList:gridColumnCount:gridPaddingHorizontal:gridPaddingVertical:colorBoxSize:textStyle:spaceBWLabelAndColorBox:legendsArrangement:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float colorBoxSize __attribute__((swift_name("colorBoxSize")));
@property (readonly) int32_t gridColumnCount __attribute__((swift_name("gridColumnCount")));
@property (readonly) float gridPaddingHorizontal __attribute__((swift_name("gridPaddingHorizontal")));
@property (readonly) float gridPaddingVertical __attribute__((swift_name("gridPaddingVertical")));
@property (readonly) NSArray<KMMYCLegendLabel *> *legendLabelList __attribute__((swift_name("legendLabelList")));
@property (readonly) id<KMMYCFoundation_layoutArrangementHorizontal> legendsArrangement __attribute__((swift_name("legendsArrangement")));
@property (readonly) float spaceBWLabelAndColorBox __attribute__((swift_name("spaceBWLabelAndColorBox")));
@property (readonly) KMMYCUi_textTextStyle *textStyle __attribute__((swift_name("textStyle")));
@end

__attribute__((swift_name("PlotData")))
@protocol KMMYCPlotData
@required
@property (readonly) id<KMMYCPlotType> plotType __attribute__((swift_name("plotType")));
@end

__attribute__((swift_name("PlotType")))
@protocol KMMYCPlotType
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlotTypeBar")))
@interface KMMYCPlotTypeBar : KMMYCBase <KMMYCPlotType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)bar __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCPlotTypeBar *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlotTypeDonut")))
@interface KMMYCPlotTypeDonut : KMMYCBase <KMMYCPlotType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)donut __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCPlotTypeDonut *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlotTypeLine")))
@interface KMMYCPlotTypeLine : KMMYCBase <KMMYCPlotType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)line __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCPlotTypeLine *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlotTypePie")))
@interface KMMYCPlotTypePie : KMMYCBase <KMMYCPlotType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)pie __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCPlotTypePie *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlotTypeWave")))
@interface KMMYCPlotTypeWave : KMMYCBase <KMMYCPlotType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)wave __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCPlotTypeWave *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Point")))
@interface KMMYCPoint : KMMYCBase
- (instancetype)initWithX:(float)x y:(float)y description:(NSString *)description __attribute__((swift_name("init(x:y:description:)"))) __attribute__((objc_designated_initializer));
- (KMMYCPoint *)doCopyX:(float)x y:(float)y description:(NSString *)description __attribute__((swift_name("doCopy(x:y:description:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *description_ __attribute__((swift_name("description_")));
@property (readonly) float x __attribute__((swift_name("x")));
@property (readonly) float y __attribute__((swift_name("y")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChartConstants")))
@interface KMMYCChartConstants : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)chartConstants __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCChartConstants *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *CHART_DESCRIPTION __attribute__((swift_name("CHART_DESCRIPTION")));
@property (readonly) int64_t DEFAULT_DETECT_DRAG_TIME_OUT __attribute__((swift_name("DEFAULT_DETECT_DRAG_TIME_OUT")));
@property (readonly) int32_t DEFAULT_YAXIS_BOTTOM_PADDING __attribute__((swift_name("DEFAULT_YAXIS_BOTTOM_PADDING")));
@property (readonly) NSString *POPUP_TOP_RIGHT_BUTTON_DESCRIPTION __attribute__((swift_name("POPUP_TOP_RIGHT_BUTTON_DESCRIPTION")));
@property (readonly) NSString *POPUP_TOP_RIGHT_BUTTON_TITLE __attribute__((swift_name("POPUP_TOP_RIGHT_BUTTON_TITLE")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Wave")))
@interface KMMYCWave : KMMYCBase
- (instancetype)initWithDataPoints:(NSArray<KMMYCPoint *> *)dataPoints waveStyle:(KMMYCLineStyle *)waveStyle intersectionPoint:(KMMYCIntersectionPoint * _Nullable)intersectionPoint selectionHighlightPoint:(KMMYCSelectionHighlightPoint * _Nullable)selectionHighlightPoint shadowUnderLine:(KMMYCShadowUnderLine * _Nullable)shadowUnderLine selectionHighlightPopUp:(KMMYCSelectionHighlightPopUp * _Nullable)selectionHighlightPopUp __attribute__((swift_name("init(dataPoints:waveStyle:intersectionPoint:selectionHighlightPoint:shadowUnderLine:selectionHighlightPopUp:)"))) __attribute__((objc_designated_initializer));
- (KMMYCWave *)doCopyDataPoints:(NSArray<KMMYCPoint *> *)dataPoints waveStyle:(KMMYCLineStyle *)waveStyle intersectionPoint:(KMMYCIntersectionPoint * _Nullable)intersectionPoint selectionHighlightPoint:(KMMYCSelectionHighlightPoint * _Nullable)selectionHighlightPoint shadowUnderLine:(KMMYCShadowUnderLine * _Nullable)shadowUnderLine selectionHighlightPopUp:(KMMYCSelectionHighlightPopUp * _Nullable)selectionHighlightPopUp __attribute__((swift_name("doCopy(dataPoints:waveStyle:intersectionPoint:selectionHighlightPoint:shadowUnderLine:selectionHighlightPopUp:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<KMMYCPoint *> *dataPoints __attribute__((swift_name("dataPoints")));
@property (readonly) KMMYCIntersectionPoint * _Nullable intersectionPoint __attribute__((swift_name("intersectionPoint")));
@property (readonly) KMMYCSelectionHighlightPoint * _Nullable selectionHighlightPoint __attribute__((swift_name("selectionHighlightPoint")));
@property (readonly) KMMYCSelectionHighlightPopUp * _Nullable selectionHighlightPopUp __attribute__((swift_name("selectionHighlightPopUp")));
@property (readonly) KMMYCShadowUnderLine * _Nullable shadowUnderLine __attribute__((swift_name("shadowUnderLine")));
@property (readonly) KMMYCLineStyle *waveStyle __attribute__((swift_name("waveStyle")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WaveChartData")))
@interface KMMYCWaveChartData : KMMYCBase
- (instancetype)initWithWavePlotData:(KMMYCWavePlotData *)wavePlotData xAxisData:(KMMYCAxisData *)xAxisData yAxisData:(KMMYCAxisData *)yAxisData isZoomAllowed:(BOOL)isZoomAllowed paddingTop:(float)paddingTop bottomPadding:(float)bottomPadding paddingRight:(float)paddingRight containerPaddingEnd:(float)containerPaddingEnd backgroundColor:(uint64_t)backgroundColor gridLines:(KMMYCGridLines * _Nullable)gridLines accessibilityConfig:(KMMYCAccessibilityConfig *)accessibilityConfig __attribute__((swift_name("init(wavePlotData:xAxisData:yAxisData:isZoomAllowed:paddingTop:bottomPadding:paddingRight:containerPaddingEnd:backgroundColor:gridLines:accessibilityConfig:)"))) __attribute__((objc_designated_initializer));
- (KMMYCWaveChartData *)doCopyWavePlotData:(KMMYCWavePlotData *)wavePlotData xAxisData:(KMMYCAxisData *)xAxisData yAxisData:(KMMYCAxisData *)yAxisData isZoomAllowed:(BOOL)isZoomAllowed paddingTop:(float)paddingTop bottomPadding:(float)bottomPadding paddingRight:(float)paddingRight containerPaddingEnd:(float)containerPaddingEnd backgroundColor:(uint64_t)backgroundColor gridLines:(KMMYCGridLines * _Nullable)gridLines accessibilityConfig:(KMMYCAccessibilityConfig *)accessibilityConfig __attribute__((swift_name("doCopy(wavePlotData:xAxisData:yAxisData:isZoomAllowed:paddingTop:bottomPadding:paddingRight:containerPaddingEnd:backgroundColor:gridLines:accessibilityConfig:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCAccessibilityConfig *accessibilityConfig __attribute__((swift_name("accessibilityConfig")));
@property (readonly) uint64_t backgroundColor __attribute__((swift_name("backgroundColor")));
@property (readonly) float bottomPadding __attribute__((swift_name("bottomPadding")));
@property (readonly) float containerPaddingEnd __attribute__((swift_name("containerPaddingEnd")));
@property (readonly) KMMYCGridLines * _Nullable gridLines __attribute__((swift_name("gridLines")));
@property (readonly) BOOL isZoomAllowed __attribute__((swift_name("isZoomAllowed")));
@property (readonly) float paddingRight __attribute__((swift_name("paddingRight")));
@property (readonly) float paddingTop __attribute__((swift_name("paddingTop")));
@property (readonly) KMMYCWavePlotData *wavePlotData __attribute__((swift_name("wavePlotData")));
@property (readonly) KMMYCAxisData *xAxisData __attribute__((swift_name("xAxisData")));
@property (readonly) KMMYCAxisData *yAxisData __attribute__((swift_name("yAxisData")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WavePlotData")))
@interface KMMYCWavePlotData : KMMYCBase <KMMYCPlotData>
- (instancetype)initWithPlotType:(id<KMMYCPlotType>)plotType lines:(NSArray<KMMYCWave *> *)lines __attribute__((swift_name("init(plotType:lines:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCWavePlotDataCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCWavePlotData *)doCopyPlotType:(id<KMMYCPlotType>)plotType lines:(NSArray<KMMYCWave *> *)lines __attribute__((swift_name("doCopy(plotType:lines:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<KMMYCWave *> *lines __attribute__((swift_name("lines")));
@property (readonly) id<KMMYCPlotType> plotType __attribute__((swift_name("plotType")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WavePlotData.Companion")))
@interface KMMYCWavePlotDataCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCWavePlotDataCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCWavePlotData *)default __attribute__((swift_name("default()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GridLines")))
@interface KMMYCGridLines : KMMYCBase
- (instancetype)initWithColor:(uint64_t)color lineWidth:(float)lineWidth pathEffect:(id<KMMYCUi_graphicsPathEffect> _Nullable)pathEffect alpha:(float)alpha colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode enableHorizontalLines:(BOOL)enableHorizontalLines enableVerticalLines:(BOOL)enableVerticalLines drawHorizontalLines:(void (^)(id<KMMYCUi_graphicsDrawScope>, KMMYCFloat *, KMMYCFloat *, KMMYCFloat *))drawHorizontalLines drawVerticalLines:(void (^)(id<KMMYCUi_graphicsDrawScope>, KMMYCFloat *, KMMYCFloat *, KMMYCFloat *))drawVerticalLines __attribute__((swift_name("init(color:lineWidth:pathEffect:alpha:colorFilter:blendMode:enableHorizontalLines:enableVerticalLines:drawHorizontalLines:drawVerticalLines:)"))) __attribute__((objc_designated_initializer));
- (KMMYCGridLines *)doCopyColor:(uint64_t)color lineWidth:(float)lineWidth pathEffect:(id<KMMYCUi_graphicsPathEffect> _Nullable)pathEffect alpha:(float)alpha colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode enableHorizontalLines:(BOOL)enableHorizontalLines enableVerticalLines:(BOOL)enableVerticalLines drawHorizontalLines:(void (^)(id<KMMYCUi_graphicsDrawScope>, KMMYCFloat *, KMMYCFloat *, KMMYCFloat *))drawHorizontalLines drawVerticalLines:(void (^)(id<KMMYCUi_graphicsDrawScope>, KMMYCFloat *, KMMYCFloat *, KMMYCFloat *))drawVerticalLines __attribute__((swift_name("doCopy(color:lineWidth:pathEffect:alpha:colorFilter:blendMode:enableHorizontalLines:enableVerticalLines:drawHorizontalLines:drawVerticalLines:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) int32_t blendMode __attribute__((swift_name("blendMode")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) KMMYCUi_graphicsColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property (readonly) void (^drawHorizontalLines)(id<KMMYCUi_graphicsDrawScope>, KMMYCFloat *, KMMYCFloat *, KMMYCFloat *) __attribute__((swift_name("drawHorizontalLines")));
@property (readonly) void (^drawVerticalLines)(id<KMMYCUi_graphicsDrawScope>, KMMYCFloat *, KMMYCFloat *, KMMYCFloat *) __attribute__((swift_name("drawVerticalLines")));
@property (readonly) BOOL enableHorizontalLines __attribute__((swift_name("enableHorizontalLines")));
@property (readonly) BOOL enableVerticalLines __attribute__((swift_name("enableVerticalLines")));
@property (readonly) float lineWidth __attribute__((swift_name("lineWidth")));
@property (readonly) id<KMMYCUi_graphicsPathEffect> _Nullable pathEffect __attribute__((swift_name("pathEffect")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IntersectionPoint")))
@interface KMMYCIntersectionPoint : KMMYCBase
- (instancetype)initWithColor:(uint64_t)color radius:(float)radius alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode draw:(void (^)(id<KMMYCUi_graphicsDrawScope>, id))draw __attribute__((swift_name("init(color:radius:alpha:style:colorFilter:blendMode:draw:)"))) __attribute__((objc_designated_initializer));
- (KMMYCIntersectionPoint *)doCopyColor:(uint64_t)color radius:(float)radius alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode draw:(void (^)(id<KMMYCUi_graphicsDrawScope>, id))draw __attribute__((swift_name("doCopy(color:radius:alpha:style:colorFilter:blendMode:draw:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) int32_t blendMode __attribute__((swift_name("blendMode")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) KMMYCUi_graphicsColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property (readonly) void (^draw)(id<KMMYCUi_graphicsDrawScope>, id) __attribute__((swift_name("draw")));
@property (readonly) float radius __attribute__((swift_name("radius")));
@property (readonly) KMMYCUi_graphicsDrawStyle *style __attribute__((swift_name("style")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Line")))
@interface KMMYCLine : KMMYCBase
- (instancetype)initWithDataPoints:(NSArray<KMMYCPoint *> *)dataPoints lineStyle:(KMMYCLineStyle *)lineStyle intersectionPoint:(KMMYCIntersectionPoint * _Nullable)intersectionPoint selectionHighlightPoint:(KMMYCSelectionHighlightPoint * _Nullable)selectionHighlightPoint shadowUnderLine:(KMMYCShadowUnderLine * _Nullable)shadowUnderLine selectionHighlightPopUp:(KMMYCSelectionHighlightPopUp * _Nullable)selectionHighlightPopUp __attribute__((swift_name("init(dataPoints:lineStyle:intersectionPoint:selectionHighlightPoint:shadowUnderLine:selectionHighlightPopUp:)"))) __attribute__((objc_designated_initializer));
- (KMMYCLine *)doCopyDataPoints:(NSArray<KMMYCPoint *> *)dataPoints lineStyle:(KMMYCLineStyle *)lineStyle intersectionPoint:(KMMYCIntersectionPoint * _Nullable)intersectionPoint selectionHighlightPoint:(KMMYCSelectionHighlightPoint * _Nullable)selectionHighlightPoint shadowUnderLine:(KMMYCShadowUnderLine * _Nullable)shadowUnderLine selectionHighlightPopUp:(KMMYCSelectionHighlightPopUp * _Nullable)selectionHighlightPopUp __attribute__((swift_name("doCopy(dataPoints:lineStyle:intersectionPoint:selectionHighlightPoint:shadowUnderLine:selectionHighlightPopUp:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<KMMYCPoint *> *dataPoints __attribute__((swift_name("dataPoints")));
@property (readonly) KMMYCIntersectionPoint * _Nullable intersectionPoint __attribute__((swift_name("intersectionPoint")));
@property (readonly) KMMYCLineStyle *lineStyle __attribute__((swift_name("lineStyle")));
@property (readonly) KMMYCSelectionHighlightPoint * _Nullable selectionHighlightPoint __attribute__((swift_name("selectionHighlightPoint")));
@property (readonly) KMMYCSelectionHighlightPopUp * _Nullable selectionHighlightPopUp __attribute__((swift_name("selectionHighlightPopUp")));
@property (readonly) KMMYCShadowUnderLine * _Nullable shadowUnderLine __attribute__((swift_name("shadowUnderLine")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LineChartData")))
@interface KMMYCLineChartData : KMMYCBase
- (instancetype)initWithLinePlotData:(KMMYCLinePlotData *)linePlotData xAxisData:(KMMYCAxisData *)xAxisData yAxisData:(KMMYCAxisData *)yAxisData isZoomAllowed:(BOOL)isZoomAllowed paddingTop:(float)paddingTop bottomPadding:(float)bottomPadding paddingRight:(float)paddingRight containerPaddingEnd:(float)containerPaddingEnd backgroundColor:(uint64_t)backgroundColor gridLines:(KMMYCGridLines * _Nullable)gridLines accessibilityConfig:(KMMYCAccessibilityConfig *)accessibilityConfig __attribute__((swift_name("init(linePlotData:xAxisData:yAxisData:isZoomAllowed:paddingTop:bottomPadding:paddingRight:containerPaddingEnd:backgroundColor:gridLines:accessibilityConfig:)"))) __attribute__((objc_designated_initializer));
- (KMMYCLineChartData *)doCopyLinePlotData:(KMMYCLinePlotData *)linePlotData xAxisData:(KMMYCAxisData *)xAxisData yAxisData:(KMMYCAxisData *)yAxisData isZoomAllowed:(BOOL)isZoomAllowed paddingTop:(float)paddingTop bottomPadding:(float)bottomPadding paddingRight:(float)paddingRight containerPaddingEnd:(float)containerPaddingEnd backgroundColor:(uint64_t)backgroundColor gridLines:(KMMYCGridLines * _Nullable)gridLines accessibilityConfig:(KMMYCAccessibilityConfig *)accessibilityConfig __attribute__((swift_name("doCopy(linePlotData:xAxisData:yAxisData:isZoomAllowed:paddingTop:bottomPadding:paddingRight:containerPaddingEnd:backgroundColor:gridLines:accessibilityConfig:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCAccessibilityConfig *accessibilityConfig __attribute__((swift_name("accessibilityConfig")));
@property (readonly) uint64_t backgroundColor __attribute__((swift_name("backgroundColor")));
@property (readonly) float bottomPadding __attribute__((swift_name("bottomPadding")));
@property (readonly) float containerPaddingEnd __attribute__((swift_name("containerPaddingEnd")));
@property (readonly) KMMYCGridLines * _Nullable gridLines __attribute__((swift_name("gridLines")));
@property (readonly) BOOL isZoomAllowed __attribute__((swift_name("isZoomAllowed")));
@property (readonly) KMMYCLinePlotData *linePlotData __attribute__((swift_name("linePlotData")));
@property (readonly) float paddingRight __attribute__((swift_name("paddingRight")));
@property (readonly) float paddingTop __attribute__((swift_name("paddingTop")));
@property (readonly) KMMYCAxisData *xAxisData __attribute__((swift_name("xAxisData")));
@property (readonly) KMMYCAxisData *yAxisData __attribute__((swift_name("yAxisData")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LinePlotData")))
@interface KMMYCLinePlotData : KMMYCBase <KMMYCPlotData>
- (instancetype)initWithPlotType:(id<KMMYCPlotType>)plotType lines:(NSArray<KMMYCLine *> *)lines __attribute__((swift_name("init(plotType:lines:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCLinePlotDataCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCLinePlotData *)doCopyPlotType:(id<KMMYCPlotType>)plotType lines:(NSArray<KMMYCLine *> *)lines __attribute__((swift_name("doCopy(plotType:lines:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<KMMYCLine *> *lines __attribute__((swift_name("lines")));
@property (readonly) id<KMMYCPlotType> plotType __attribute__((swift_name("plotType")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LinePlotData.Companion")))
@interface KMMYCLinePlotDataCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCLinePlotDataCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCLinePlotData *)default __attribute__((swift_name("default()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LineStyle")))
@interface KMMYCLineStyle : KMMYCBase
- (instancetype)initWithLineType:(KMMYCLineType *)lineType color:(uint64_t)color width:(float)width alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("init(lineType:color:width:alpha:style:colorFilter:blendMode:)"))) __attribute__((objc_designated_initializer));
- (KMMYCLineStyle *)doCopyLineType:(KMMYCLineType *)lineType color:(uint64_t)color width:(float)width alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("doCopy(lineType:color:width:alpha:style:colorFilter:blendMode:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) int32_t blendMode __attribute__((swift_name("blendMode")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) KMMYCUi_graphicsColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property (readonly) KMMYCLineType *lineType __attribute__((swift_name("lineType")));
@property (readonly) KMMYCUi_graphicsDrawStyle *style __attribute__((swift_name("style")));
@property (readonly) float width __attribute__((swift_name("width")));
@end

__attribute__((swift_name("LineType")))
@interface KMMYCLineType : KMMYCBase
@property KMMYCKotlinFloatArray *intervals __attribute__((swift_name("intervals")));
@property (readonly) BOOL isDotted __attribute__((swift_name("isDotted")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LineType.SmoothCurve")))
@interface KMMYCLineTypeSmoothCurve : KMMYCLineType
- (instancetype)initWithIsDotted:(BOOL)isDotted intervals:(KMMYCKotlinFloatArray *)intervals __attribute__((swift_name("init(isDotted:intervals:)"))) __attribute__((objc_designated_initializer));
- (KMMYCLineTypeSmoothCurve *)doCopyIsDotted:(BOOL)isDotted intervals:(KMMYCKotlinFloatArray *)intervals __attribute__((swift_name("doCopy(isDotted:intervals:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property KMMYCKotlinFloatArray *intervals __attribute__((swift_name("intervals")));
@property (readonly) BOOL isDotted __attribute__((swift_name("isDotted")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LineType.Straight")))
@interface KMMYCLineTypeStraight : KMMYCLineType
- (instancetype)initWithIsDotted:(BOOL)isDotted intervals:(KMMYCKotlinFloatArray *)intervals __attribute__((swift_name("init(isDotted:intervals:)"))) __attribute__((objc_designated_initializer));
- (KMMYCLineTypeStraight *)doCopyIsDotted:(BOOL)isDotted intervals:(KMMYCKotlinFloatArray *)intervals __attribute__((swift_name("doCopy(isDotted:intervals:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property KMMYCKotlinFloatArray *intervals __attribute__((swift_name("intervals")));
@property (readonly) BOOL isDotted __attribute__((swift_name("isDotted")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectionHighlightPoint")))
@interface KMMYCSelectionHighlightPoint : KMMYCBase
- (instancetype)initWithColor:(uint64_t)color radius:(float)radius alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode draw:(void (^)(id<KMMYCUi_graphicsDrawScope>, id))draw isHighlightLineRequired:(BOOL)isHighlightLineRequired drawHighlightLine:(void (^)(id<KMMYCUi_graphicsDrawScope>, id, id))drawHighlightLine __attribute__((swift_name("init(color:radius:alpha:style:colorFilter:blendMode:draw:isHighlightLineRequired:drawHighlightLine:)"))) __attribute__((objc_designated_initializer));
- (KMMYCSelectionHighlightPoint *)doCopyColor:(uint64_t)color radius:(float)radius alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode draw:(void (^)(id<KMMYCUi_graphicsDrawScope>, id))draw isHighlightLineRequired:(BOOL)isHighlightLineRequired drawHighlightLine:(void (^)(id<KMMYCUi_graphicsDrawScope>, id, id))drawHighlightLine __attribute__((swift_name("doCopy(color:radius:alpha:style:colorFilter:blendMode:draw:isHighlightLineRequired:drawHighlightLine:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) int32_t blendMode __attribute__((swift_name("blendMode")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) KMMYCUi_graphicsColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property (readonly) void (^draw)(id<KMMYCUi_graphicsDrawScope>, id) __attribute__((swift_name("draw")));
@property (readonly) void (^drawHighlightLine)(id<KMMYCUi_graphicsDrawScope>, id, id) __attribute__((swift_name("drawHighlightLine")));
@property (readonly) BOOL isHighlightLineRequired __attribute__((swift_name("isHighlightLineRequired")));
@property (readonly) float radius __attribute__((swift_name("radius")));
@property (readonly) KMMYCUi_graphicsDrawStyle *style __attribute__((swift_name("style")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectionHighlightPopUp")))
@interface KMMYCSelectionHighlightPopUp : KMMYCBase
- (instancetype)initWithBackgroundColor:(uint64_t)backgroundColor backgroundAlpha:(float)backgroundAlpha backgroundCornerRadius:(int64_t)backgroundCornerRadius backgroundColorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)backgroundColorFilter backgroundBlendMode:(int32_t)backgroundBlendMode backgroundStyle:(KMMYCUi_graphicsDrawStyle *)backgroundStyle paddingBetweenPopUpAndPoint:(float)paddingBetweenPopUpAndPoint labelSize:(int64_t)labelSize labelColor:(uint64_t)labelColor labelAlignment:(float)labelAlignment popUpLabel:(NSString *(^)(KMMYCFloat *, KMMYCFloat *))popUpLabel draw:(void (^)(id<KMMYCUi_graphicsDrawScope>, id, KMMYCPoint *, KMMYCUi_textTextMeasurer *))draw __attribute__((swift_name("init(backgroundColor:backgroundAlpha:backgroundCornerRadius:backgroundColorFilter:backgroundBlendMode:backgroundStyle:paddingBetweenPopUpAndPoint:labelSize:labelColor:labelAlignment:popUpLabel:draw:)"))) __attribute__((objc_designated_initializer));
- (KMMYCSelectionHighlightPopUp *)doCopyBackgroundColor:(uint64_t)backgroundColor backgroundAlpha:(float)backgroundAlpha backgroundCornerRadius:(int64_t)backgroundCornerRadius backgroundColorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)backgroundColorFilter backgroundBlendMode:(int32_t)backgroundBlendMode backgroundStyle:(KMMYCUi_graphicsDrawStyle *)backgroundStyle paddingBetweenPopUpAndPoint:(float)paddingBetweenPopUpAndPoint labelSize:(int64_t)labelSize labelColor:(uint64_t)labelColor labelAlignment:(float)labelAlignment popUpLabel:(NSString *(^)(KMMYCFloat *, KMMYCFloat *))popUpLabel draw:(void (^)(id<KMMYCUi_graphicsDrawScope>, id, KMMYCPoint *, KMMYCUi_textTextMeasurer *))draw __attribute__((swift_name("doCopy(backgroundColor:backgroundAlpha:backgroundCornerRadius:backgroundColorFilter:backgroundBlendMode:backgroundStyle:paddingBetweenPopUpAndPoint:labelSize:labelColor:labelAlignment:popUpLabel:draw:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float backgroundAlpha __attribute__((swift_name("backgroundAlpha")));
@property (readonly) int32_t backgroundBlendMode __attribute__((swift_name("backgroundBlendMode")));
@property (readonly) uint64_t backgroundColor __attribute__((swift_name("backgroundColor")));
@property (readonly) KMMYCUi_graphicsColorFilter * _Nullable backgroundColorFilter __attribute__((swift_name("backgroundColorFilter")));
@property (readonly) int64_t backgroundCornerRadius __attribute__((swift_name("backgroundCornerRadius")));
@property (readonly) KMMYCUi_graphicsDrawStyle *backgroundStyle __attribute__((swift_name("backgroundStyle")));
@property (readonly) void (^draw)(id<KMMYCUi_graphicsDrawScope>, id, KMMYCPoint *, KMMYCUi_textTextMeasurer *) __attribute__((swift_name("draw")));
@property (readonly) float labelAlignment __attribute__((swift_name("labelAlignment")));
@property (readonly) uint64_t labelColor __attribute__((swift_name("labelColor")));
@property (readonly) int64_t labelSize __attribute__((swift_name("labelSize")));
@property (readonly) float paddingBetweenPopUpAndPoint __attribute__((swift_name("paddingBetweenPopUpAndPoint")));
@property (readonly) NSString *(^popUpLabel)(KMMYCFloat *, KMMYCFloat *) __attribute__((swift_name("popUpLabel")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ShadowUnderLine")))
@interface KMMYCShadowUnderLine : KMMYCBase
- (instancetype)initWithColor:(uint64_t)color brush:(KMMYCUi_graphicsBrush * _Nullable)brush alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode draw:(void (^)(id<KMMYCUi_graphicsDrawScope>, id<KMMYCUi_graphicsPath>))draw __attribute__((swift_name("init(color:brush:alpha:style:colorFilter:blendMode:draw:)"))) __attribute__((objc_designated_initializer));
- (KMMYCShadowUnderLine *)doCopyColor:(uint64_t)color brush:(KMMYCUi_graphicsBrush * _Nullable)brush alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode draw:(void (^)(id<KMMYCUi_graphicsDrawScope>, id<KMMYCUi_graphicsPath>))draw __attribute__((swift_name("doCopy(color:brush:alpha:style:colorFilter:blendMode:draw:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) int32_t blendMode __attribute__((swift_name("blendMode")));
@property (readonly) KMMYCUi_graphicsBrush * _Nullable brush __attribute__((swift_name("brush")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) KMMYCUi_graphicsColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property (readonly) void (^draw)(id<KMMYCUi_graphicsDrawScope>, id<KMMYCUi_graphicsPath>) __attribute__((swift_name("draw")));
@property (readonly) KMMYCUi_graphicsDrawStyle *style __attribute__((swift_name("style")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DecimalFormat")))
@interface KMMYCDecimalFormat : KMMYCBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)formatDouble:(float)double_ __attribute__((swift_name("format(double:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Main_iosKt")))
@interface KMMYCMain_iosKt : KMMYCBase
+ (UIViewController *)MainViewController __attribute__((swift_name("MainViewController()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ScrollableCanvasContainerKt")))
@interface KMMYCScrollableCanvasContainerKt : KMMYCBase
+ (float)checkAndGetMaxScrollOffsetCurrentScrollOffset:(float)currentScrollOffset computedMaxScrollOffset:(float)computedMaxScrollOffset __attribute__((swift_name("checkAndGetMaxScrollOffset(currentScrollOffset:computedMaxScrollOffset:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("YAxisKt")))
@interface KMMYCYAxisKt : KMMYCBase
+ (KMMYCKotlinPair<KMMYCFloat *, KMMYCFloat *> *)getAxisInitValuesAxisData:(KMMYCAxisData *)axisData canvasHeight:(float)canvasHeight bottomPadding:(float)bottomPadding topPadding:(float)topPadding isDataCategoryInYAxis:(BOOL)isDataCategoryInYAxis dataCategoryWidth:(float)dataCategoryWidth __attribute__((swift_name("getAxisInitValues(axisData:canvasHeight:bottomPadding:topPadding:isDataCategoryInYAxis:dataCategoryWidth:)")));
+ (KMMYCKotlinTriple<KMMYCFloat *, KMMYCFloat *, KMMYCFloat *> *)getYAxisScalePoints:(NSArray<KMMYCPoint *> *)points steps:(int32_t)steps __attribute__((swift_name("getYAxisScale(points:steps:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("XAxisKt")))
@interface KMMYCXAxisKt : KMMYCBase
+ (KMMYCKotlinTriple<KMMYCFloat *, KMMYCFloat *, KMMYCFloat *> *)getXAxisScalePoints:(NSArray<KMMYCPoint *> *)points steps:(int32_t)steps __attribute__((swift_name("getXAxisScale(points:steps:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformKt")))
@interface KMMYCPlatformKt : KMMYCBase
+ (id<KMMYCPlatform>)getPlatform __attribute__((swift_name("getPlatform()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExtensionsKt")))
@interface KMMYCExtensionsKt : KMMYCBase
+ (NSArray<KMMYCPoint *> *)getLineChartDataListSize:(int32_t)listSize start:(int32_t)start maxRange:(int32_t)maxRange __attribute__((swift_name("getLineChartData(listSize:start:maxRange:)")));
+ (int32_t)getMaxElementInXAxisXMax:(float)xMax xStepSize:(int32_t)xStepSize __attribute__((swift_name("getMaxElementInXAxis(xMax:xStepSize:)")));
+ (int32_t)getMaxElementInYAxisYMax:(float)yMax yStepSize:(int32_t)yStepSize __attribute__((swift_name("getMaxElementInYAxis(yMax:yStepSize:)")));
+ (KMMYCUi_geometryRect *)getTextBackgroundRectX:(float)x y:(float)y text:(NSString *)text paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("getTextBackgroundRect(x:y:text:paint:)")));
+ (KMMYCKotlinPair<KMMYCFloat *, KMMYCFloat *> *)getXMaxAndMinPointsPoints:(NSArray<KMMYCPoint *> *)points __attribute__((swift_name("getXMaxAndMinPoints(points:)")));
+ (KMMYCKotlinPair<KMMYCFloat *, KMMYCFloat *> *)getYMaxAndMinPointsPoints:(NSArray<KMMYCPoint *> *)points __attribute__((swift_name("getYMaxAndMinPoints(points:)")));
+ (void)drawGridLines:(id<KMMYCUi_graphicsDrawScope>)receiver yBottom:(float)yBottom top:(float)top xLeft:(float)xLeft paddingRight:(float)paddingRight scrollOffset:(float)scrollOffset verticalPointsSize:(int32_t)verticalPointsSize xZoom:(float)xZoom xAxisScale:(float)xAxisScale ySteps:(int32_t)ySteps xAxisStepSize:(float)xAxisStepSize gridLines:(KMMYCGridLines *)gridLines __attribute__((swift_name("drawGridLines(_:yBottom:top:xLeft:paddingRight:scrollOffset:verticalPointsSize:xZoom:xAxisScale:ySteps:xAxisStepSize:gridLines:)")));
+ (NSString *)formatToSinglePrecision:(float)receiver __attribute__((swift_name("formatToSinglePrecision(_:)")));
+ (BOOL)isDragLocked:(int64_t)receiver dragOffset:(float)dragOffset xOffset:(float)xOffset __attribute__((swift_name("isDragLocked(_:dragOffset:xOffset:)")));
+ (BOOL)isNotNull:(id _Nullable)receiver __attribute__((swift_name("isNotNull(_:)")));
+ (BOOL)isPointTapped:(int64_t)receiver tapOffset:(int64_t)tapOffset tapPadding:(float)tapPadding __attribute__((swift_name("isPointTapped(_:tapOffset:tapPadding:)")));
+ (BOOL)isTapped:(int64_t)receiver tapOffset:(int64_t)tapOffset xOffset:(float)xOffset bottom:(float)bottom tapPadding:(float)tapPadding __attribute__((swift_name("isTapped(_:tapOffset:xOffset:bottom:tapPadding:)")));
+ (BOOL)isYAxisTapped:(int64_t)receiver tapOffset:(int64_t)tapOffset yOffset:(float)yOffset left:(float)left tapPadding:(float)tapPadding xAxisWidth:(float)xAxisWidth __attribute__((swift_name("isYAxisTapped(_:tapOffset:yOffset:left:tapPadding:xAxisWidth:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LineChartKt")))
@interface KMMYCLineChartKt : KMMYCBase
+ (KMMYCKotlinPair<NSMutableArray<id> *, NSMutableArray<id> *> *)getCubicPointsPointsData:(NSArray<id> *)pointsData __attribute__((swift_name("getCubicPoints(pointsData:)")));
+ (NSMutableArray<id> *)getMappingPointsToGraphLineChartPoints:(NSArray<KMMYCPoint *> *)lineChartPoints xMin:(float)xMin xOffset:(float)xOffset xLeft:(float)xLeft scrollOffset:(float)scrollOffset yBottom:(float)yBottom yMin:(float)yMin yOffset:(float)yOffset __attribute__((swift_name("getMappingPointsToGraph(lineChartPoints:xMin:xOffset:xLeft:scrollOffset:yBottom:yMin:yOffset:)")));
+ (float)getMaxScrollDistanceColumnWidth:(float)columnWidth xMax:(float)xMax xMin:(float)xMin xOffset:(float)xOffset paddingRight:(float)paddingRight canvasWidth:(float)canvasWidth containerPaddingEnd:(float)containerPaddingEnd __attribute__((swift_name("getMaxScrollDistance(columnWidth:xMax:xMin:xOffset:paddingRight:canvasWidth:containerPaddingEnd:)")));
+ (void)drawHighLightOnSelectedPoint:(id<KMMYCUi_graphicsDrawScope>)receiver dragLocks:(KMMYCMutableDictionary<KMMYCInt *, KMMYCKotlinPair<KMMYCPoint *, id> *> *)dragLocks columnWidth:(float)columnWidth paddingRight:(float)paddingRight yBottom:(float)yBottom selectionHighlightPoint:(KMMYCSelectionHighlightPoint * _Nullable)selectionHighlightPoint __attribute__((swift_name("drawHighLightOnSelectedPoint(_:dragLocks:columnWidth:paddingRight:yBottom:selectionHighlightPoint:)")));
+ (void)drawHighlightText:(id<KMMYCUi_graphicsDrawScope>)receiver identifiedPoint:(KMMYCPoint *)identifiedPoint selectedOffset:(int64_t)selectedOffset selectionHighlightPopUp:(KMMYCSelectionHighlightPopUp * _Nullable)selectionHighlightPopUp textMeasurer:(KMMYCUi_textTextMeasurer *)textMeasurer __attribute__((swift_name("drawHighlightText(_:identifiedPoint:selectedOffset:selectionHighlightPopUp:textMeasurer:)")));
+ (void)drawPointOnLine:(id<KMMYCUi_graphicsDrawScope>)receiver offset:(int64_t)offset intersectionPoint:(KMMYCIntersectionPoint * _Nullable)intersectionPoint __attribute__((swift_name("drawPointOnLine(_:offset:intersectionPoint:)")));
+ (void)drawShadowUnderLineAndIntersectionPoint:(id<KMMYCUi_graphicsDrawScope>)receiver cubicPath:(id<KMMYCUi_graphicsPath>)cubicPath pointsData:(NSMutableArray<id> *)pointsData yBottom:(float)yBottom line:(KMMYCLine *)line __attribute__((swift_name("drawShadowUnderLineAndIntersectionPoint(_:cubicPath:pointsData:yBottom:line:)")));
+ (id<KMMYCUi_graphicsPath>)drawStraightOrCubicLine:(id<KMMYCUi_graphicsDrawScope>)receiver pointsData:(NSMutableArray<id> *)pointsData cubicPoints1:(NSMutableArray<id> *)cubicPoints1 cubicPoints2:(NSMutableArray<id> *)cubicPoints2 lineStyle:(KMMYCLineStyle *)lineStyle __attribute__((swift_name("drawStraightOrCubicLine(_:pointsData:cubicPoints1:cubicPoints2:lineStyle:)")));
+ (void)drawUnderScrollMask:(id<KMMYCUi_graphicsDrawScope>)receiver columnWidth:(float)columnWidth paddingRight:(float)paddingRight bgColor:(uint64_t)bgColor __attribute__((swift_name("drawUnderScrollMask(_:columnWidth:paddingRight:bgColor:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LineChartExtensionsKt")))
@interface KMMYCLineChartExtensionsKt : KMMYCBase
+ (float)getMaxElementInYAxisOffset:(float)offset steps:(int32_t)steps __attribute__((swift_name("getMaxElementInYAxis(offset:steps:)")));
+ (KMMYCKotlinTriple<KMMYCFloat *, KMMYCFloat *, KMMYCFloat *> *)getYAxisScalePoints:(NSArray<KMMYCPoint *> *)points steps:(int32_t)steps __attribute__((swift_name("getYAxisScale(points:steps:)")));
+ (BOOL)isTapped:(int64_t)receiver tapOffset:(float)tapOffset xOffset:(float)xOffset __attribute__((swift_name("isTapped(_:tapOffset:xOffset:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface KMMYCKotlinEnumCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface KMMYCKotlinArray<T> : KMMYCBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(KMMYCInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<KMMYCKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextStyle")))
@interface KMMYCUi_textTextStyle : KMMYCBase
- (instancetype)initWithColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow textAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent __attribute__((swift_name("init(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:textAlign:textDirection:lineHeight:textIndent:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow textAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle __attribute__((swift_name("init(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow textAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(KMMYCUi_textLineBreak * _Nullable)lineBreak hyphens:(KMMYCUi_textHyphens * _Nullable)hyphens __attribute__((swift_name("init(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithBrush:(KMMYCUi_graphicsBrush * _Nullable)brush alpha:(float)alpha fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow textAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(KMMYCUi_textLineBreak * _Nullable)lineBreak hyphens:(KMMYCUi_textHyphens * _Nullable)hyphens __attribute__((swift_name("init(brush:alpha:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_textTextStyleCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCUi_textTextStyle *)doCopyBrush:(KMMYCUi_graphicsBrush * _Nullable)brush alpha:(float)alpha fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow textAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(KMMYCUi_textLineBreak * _Nullable)lineBreak hyphens:(KMMYCUi_textHyphens * _Nullable)hyphens __attribute__((swift_name("doCopy(brush:alpha:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:)")));
- (KMMYCUi_textTextStyle *)doCopyColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow textAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent __attribute__((swift_name("doCopy(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:textAlign:textDirection:lineHeight:textIndent:)")));
- (KMMYCUi_textTextStyle *)doCopyColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow textAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle __attribute__((swift_name("doCopy(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:)")));
- (KMMYCUi_textTextStyle *)doCopyColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow textAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(KMMYCUi_textLineBreak * _Nullable)lineBreak hyphens:(KMMYCUi_textHyphens * _Nullable)hyphens __attribute__((swift_name("doCopy(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (BOOL)hasSameLayoutAffectingAttributesOther:(KMMYCUi_textTextStyle *)other __attribute__((swift_name("hasSameLayoutAffectingAttributes(other:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textTextStyle *)mergeOther:(KMMYCUi_textParagraphStyle *)other __attribute__((swift_name("merge(other:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textTextStyle *)mergeOther_:(KMMYCUi_textSpanStyle *)other __attribute__((swift_name("merge(other_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textTextStyle *)mergeOther__:(KMMYCUi_textTextStyle * _Nullable)other __attribute__((swift_name("merge(other__:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textTextStyle *)plusOther:(KMMYCUi_textParagraphStyle *)other __attribute__((swift_name("plus(other:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textTextStyle *)plusOther_:(KMMYCUi_textSpanStyle *)other __attribute__((swift_name("plus(other_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textTextStyle *)plusOther__:(KMMYCUi_textTextStyle *)other __attribute__((swift_name("plus(other__:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textParagraphStyle *)toParagraphStyle __attribute__((swift_name("toParagraphStyle()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textSpanStyle *)toSpanStyle __attribute__((swift_name("toSpanStyle()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) uint64_t background __attribute__((swift_name("background")));
@property (readonly) id _Nullable baselineShift __attribute__((swift_name("baselineShift")));
@property (readonly) KMMYCUi_graphicsBrush * _Nullable brush __attribute__((swift_name("brush")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) KMMYCUi_textFontFamily * _Nullable fontFamily __attribute__((swift_name("fontFamily")));
@property (readonly) NSString * _Nullable fontFeatureSettings __attribute__((swift_name("fontFeatureSettings")));
@property (readonly) int64_t fontSize __attribute__((swift_name("fontSize")));
@property (readonly) id _Nullable fontStyle __attribute__((swift_name("fontStyle")));
@property (readonly) id _Nullable fontSynthesis __attribute__((swift_name("fontSynthesis")));
@property (readonly) KMMYCUi_textFontWeight * _Nullable fontWeight __attribute__((swift_name("fontWeight")));
@property (readonly) KMMYCUi_textHyphens * _Nullable hyphens __attribute__((swift_name("hyphens")));
@property (readonly) int64_t letterSpacing __attribute__((swift_name("letterSpacing")));
@property (readonly) KMMYCUi_textLineBreak * _Nullable lineBreak __attribute__((swift_name("lineBreak")));
@property (readonly) int64_t lineHeight __attribute__((swift_name("lineHeight")));
@property (readonly) KMMYCUi_textLineHeightStyle * _Nullable lineHeightStyle __attribute__((swift_name("lineHeightStyle")));
@property (readonly) KMMYCUi_textLocaleList * _Nullable localeList __attribute__((swift_name("localeList")));
@property (readonly) KMMYCUi_textPlatformTextStyle * _Nullable platformStyle __attribute__((swift_name("platformStyle")));
@property (readonly) KMMYCUi_graphicsShadow * _Nullable shadow __attribute__((swift_name("shadow")));
@property (readonly) id _Nullable textAlign __attribute__((swift_name("textAlign")));
@property (readonly) KMMYCUi_textTextDecoration * _Nullable textDecoration __attribute__((swift_name("textDecoration")));
@property (readonly) id _Nullable textDirection __attribute__((swift_name("textDirection")));
@property (readonly) KMMYCUi_textTextGeometricTransform * _Nullable textGeometricTransform __attribute__((swift_name("textGeometricTransform")));
@property (readonly) KMMYCUi_textTextIndent * _Nullable textIndent __attribute__((swift_name("textIndent")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
__attribute__((swift_name("Foundation_layoutArrangementHorizontal")))
@protocol KMMYCFoundation_layoutArrangementHorizontal
@required
- (void)arrange:(id<KMMYCUi_unitDensity>)receiver totalSize:(int32_t)totalSize sizes:(KMMYCKotlinIntArray *)sizes layoutDirection:(KMMYCUi_unitLayoutDirection *)layoutDirection outPositions:(KMMYCKotlinIntArray *)outPositions __attribute__((swift_name("arrange(_:totalSize:sizes:layoutDirection:outPositions:)")));
@property (readonly) float spacing __attribute__((swift_name("spacing")));
@end

__attribute__((swift_name("Ui_graphicsPathEffect")))
@protocol KMMYCUi_graphicsPathEffect
@required
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsColorFilter")))
@interface KMMYCUi_graphicsColorFilter : KMMYCBase
@property (class, readonly, getter=companion) KMMYCUi_graphicsColorFilterCompanion *companion __attribute__((swift_name("companion")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((swift_name("Ui_unitDensity")))
@protocol KMMYCUi_unitDensity
@required

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (int32_t)roundToPx:(float)receiver __attribute__((swift_name("roundToPx(_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (int32_t)roundToPx_:(int64_t)receiver __attribute__((swift_name("roundToPx(__:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (float)toDp:(int64_t)receiver __attribute__((swift_name("toDp(_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (float)toDp_:(float)receiver __attribute__((swift_name("toDp(__:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (float)toDp__:(int32_t)receiver __attribute__((swift_name("toDp(___:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (int64_t)toDpSize:(int64_t)receiver __attribute__((swift_name("toDpSize(_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (float)toPx:(float)receiver __attribute__((swift_name("toPx(_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (float)toPx_:(int64_t)receiver __attribute__((swift_name("toPx(__:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_geometryRect *)toRect:(KMMYCUi_unitDpRect *)receiver __attribute__((swift_name("toRect(_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (int64_t)toSize:(int64_t)receiver __attribute__((swift_name("toSize(_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (int64_t)toSp:(float)receiver __attribute__((swift_name("toSp(_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (int64_t)toSp_:(float)receiver __attribute__((swift_name("toSp(__:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (int64_t)toSp__:(int32_t)receiver __attribute__((swift_name("toSp(___:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float density __attribute__((swift_name("density")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float fontScale __attribute__((swift_name("fontScale")));
@end

__attribute__((swift_name("Ui_graphicsDrawScope")))
@protocol KMMYCUi_graphicsDrawScope <KMMYCUi_unitDensity>
@required
- (void)drawArcBrush:(KMMYCUi_graphicsBrush *)brush startAngle:(float)startAngle sweepAngle:(float)sweepAngle useCenter:(BOOL)useCenter topLeft:(int64_t)topLeft size:(int64_t)size alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawArc(brush:startAngle:sweepAngle:useCenter:topLeft:size:alpha:style:colorFilter:blendMode:)")));
- (void)drawArcColor:(uint64_t)color startAngle:(float)startAngle sweepAngle:(float)sweepAngle useCenter:(BOOL)useCenter topLeft:(int64_t)topLeft size:(int64_t)size alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawArc(color:startAngle:sweepAngle:useCenter:topLeft:size:alpha:style:colorFilter:blendMode:)")));
- (void)drawCircleBrush:(KMMYCUi_graphicsBrush *)brush radius:(float)radius center:(int64_t)center alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawCircle(brush:radius:center:alpha:style:colorFilter:blendMode:)")));
- (void)drawCircleColor:(uint64_t)color radius:(float)radius center:(int64_t)center alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawCircle(color:radius:center:alpha:style:colorFilter:blendMode:)")));
- (void)drawImageImage:(id<KMMYCUi_graphicsImageBitmap>)image topLeft:(int64_t)topLeft alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawImage(image:topLeft:alpha:style:colorFilter:blendMode:)")));
- (void)drawImageImage:(id<KMMYCUi_graphicsImageBitmap>)image srcOffset:(int64_t)srcOffset srcSize:(int64_t)srcSize dstOffset:(int64_t)dstOffset dstSize:(int64_t)dstSize alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode filterQuality:(int32_t)filterQuality __attribute__((swift_name("drawImage(image:srcOffset:srcSize:dstOffset:dstSize:alpha:style:colorFilter:blendMode:filterQuality:)")));
- (void)drawLineBrush:(KMMYCUi_graphicsBrush *)brush start:(int64_t)start end:(int64_t)end strokeWidth:(float)strokeWidth cap:(int32_t)cap pathEffect:(id<KMMYCUi_graphicsPathEffect> _Nullable)pathEffect alpha:(float)alpha colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawLine(brush:start:end:strokeWidth:cap:pathEffect:alpha:colorFilter:blendMode:)")));
- (void)drawLineColor:(uint64_t)color start:(int64_t)start end:(int64_t)end strokeWidth:(float)strokeWidth cap:(int32_t)cap pathEffect:(id<KMMYCUi_graphicsPathEffect> _Nullable)pathEffect alpha:(float)alpha colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawLine(color:start:end:strokeWidth:cap:pathEffect:alpha:colorFilter:blendMode:)")));
- (void)drawOvalBrush:(KMMYCUi_graphicsBrush *)brush topLeft:(int64_t)topLeft size:(int64_t)size alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawOval(brush:topLeft:size:alpha:style:colorFilter:blendMode:)")));
- (void)drawOvalColor:(uint64_t)color topLeft:(int64_t)topLeft size:(int64_t)size alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawOval(color:topLeft:size:alpha:style:colorFilter:blendMode:)")));
- (void)drawPathPath:(id<KMMYCUi_graphicsPath>)path brush:(KMMYCUi_graphicsBrush *)brush alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawPath(path:brush:alpha:style:colorFilter:blendMode:)")));
- (void)drawPathPath:(id<KMMYCUi_graphicsPath>)path color:(uint64_t)color alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawPath(path:color:alpha:style:colorFilter:blendMode:)")));
- (void)drawPointsPoints:(NSArray<id> *)points pointMode:(int32_t)pointMode brush:(KMMYCUi_graphicsBrush *)brush strokeWidth:(float)strokeWidth cap:(int32_t)cap pathEffect:(id<KMMYCUi_graphicsPathEffect> _Nullable)pathEffect alpha:(float)alpha colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawPoints(points:pointMode:brush:strokeWidth:cap:pathEffect:alpha:colorFilter:blendMode:)")));
- (void)drawPointsPoints:(NSArray<id> *)points pointMode:(int32_t)pointMode color:(uint64_t)color strokeWidth:(float)strokeWidth cap:(int32_t)cap pathEffect:(id<KMMYCUi_graphicsPathEffect> _Nullable)pathEffect alpha:(float)alpha colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawPoints(points:pointMode:color:strokeWidth:cap:pathEffect:alpha:colorFilter:blendMode:)")));
- (void)drawRectBrush:(KMMYCUi_graphicsBrush *)brush topLeft:(int64_t)topLeft size:(int64_t)size alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawRect(brush:topLeft:size:alpha:style:colorFilter:blendMode:)")));
- (void)drawRectColor:(uint64_t)color topLeft:(int64_t)topLeft size:(int64_t)size alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawRect(color:topLeft:size:alpha:style:colorFilter:blendMode:)")));
- (void)drawRoundRectBrush:(KMMYCUi_graphicsBrush *)brush topLeft:(int64_t)topLeft size:(int64_t)size cornerRadius:(int64_t)cornerRadius alpha:(float)alpha style:(KMMYCUi_graphicsDrawStyle *)style colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawRoundRect(brush:topLeft:size:cornerRadius:alpha:style:colorFilter:blendMode:)")));
- (void)drawRoundRectColor:(uint64_t)color topLeft:(int64_t)topLeft size:(int64_t)size cornerRadius:(int64_t)cornerRadius style:(KMMYCUi_graphicsDrawStyle *)style alpha:(float)alpha colorFilter:(KMMYCUi_graphicsColorFilter * _Nullable)colorFilter blendMode:(int32_t)blendMode __attribute__((swift_name("drawRoundRect(color:topLeft:size:cornerRadius:style:alpha:colorFilter:blendMode:)")));
@property (readonly) int64_t center __attribute__((swift_name("center")));
@property (readonly) id<KMMYCUi_graphicsDrawContext> drawContext __attribute__((swift_name("drawContext")));
@property (readonly) KMMYCUi_unitLayoutDirection *layoutDirection __attribute__((swift_name("layoutDirection")));
@property (readonly) int64_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("Ui_graphicsDrawStyle")))
@interface KMMYCUi_graphicsDrawStyle : KMMYCBase
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinFloatArray")))
@interface KMMYCKotlinFloatArray : KMMYCBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(KMMYCFloat *(^)(KMMYCInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (float)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (KMMYCKotlinFloatIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(float)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextMeasurer")))
@interface KMMYCUi_textTextMeasurer : KMMYCBase
- (instancetype)initWithFallbackFontFamilyResolver:(id<KMMYCUi_textFontFamilyResolver>)fallbackFontFamilyResolver fallbackDensity:(id<KMMYCUi_unitDensity>)fallbackDensity fallbackLayoutDirection:(KMMYCUi_unitLayoutDirection *)fallbackLayoutDirection cacheSize:(int32_t)cacheSize __attribute__((swift_name("init(fallbackFontFamilyResolver:fallbackDensity:fallbackLayoutDirection:cacheSize:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textTextLayoutResult *)measureText:(KMMYCUi_textAnnotatedString *)text style:(KMMYCUi_textTextStyle *)style overflow:(int32_t)overflow softWrap:(BOOL)softWrap maxLines:(int32_t)maxLines placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders constraints:(int64_t)constraints layoutDirection:(KMMYCUi_unitLayoutDirection *)layoutDirection density:(id<KMMYCUi_unitDensity>)density fontFamilyResolver:(id<KMMYCUi_textFontFamilyResolver>)fontFamilyResolver skipCache:(BOOL)skipCache __attribute__((swift_name("measure(text:style:overflow:softWrap:maxLines:placeholders:constraints:layoutDirection:density:fontFamilyResolver:skipCache:)")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((swift_name("Ui_graphicsBrush")))
@interface KMMYCUi_graphicsBrush : KMMYCBase
@property (class, readonly, getter=companion) KMMYCUi_graphicsBrushCompanion *companion __attribute__((swift_name("companion")));
- (void)applyToSize:(int64_t)size p:(id<KMMYCUi_graphicsPaint>)p alpha:(float)alpha __attribute__((swift_name("applyTo(size:p:alpha:)")));
@property (readonly) int64_t intrinsicSize __attribute__((swift_name("intrinsicSize")));
@end

__attribute__((swift_name("Ui_graphicsPath")))
@protocol KMMYCUi_graphicsPath
@required
- (void)addArcOval:(KMMYCUi_geometryRect *)oval startAngleDegrees:(float)startAngleDegrees sweepAngleDegrees:(float)sweepAngleDegrees __attribute__((swift_name("addArc(oval:startAngleDegrees:sweepAngleDegrees:)")));
- (void)addArcRadOval:(KMMYCUi_geometryRect *)oval startAngleRadians:(float)startAngleRadians sweepAngleRadians:(float)sweepAngleRadians __attribute__((swift_name("addArcRad(oval:startAngleRadians:sweepAngleRadians:)")));
- (void)addOvalOval:(KMMYCUi_geometryRect *)oval __attribute__((swift_name("addOval(oval:)")));
- (void)addPathPath:(id<KMMYCUi_graphicsPath>)path offset:(int64_t)offset __attribute__((swift_name("addPath(path:offset:)")));
- (void)addRectRect:(KMMYCUi_geometryRect *)rect __attribute__((swift_name("addRect(rect:)")));
- (void)addRoundRectRoundRect:(KMMYCUi_geometryRoundRect *)roundRect __attribute__((swift_name("addRoundRect(roundRect:)")));
- (void)arcToRect:(KMMYCUi_geometryRect *)rect startAngleDegrees:(float)startAngleDegrees sweepAngleDegrees:(float)sweepAngleDegrees forceMoveTo:(BOOL)forceMoveTo __attribute__((swift_name("arcTo(rect:startAngleDegrees:sweepAngleDegrees:forceMoveTo:)")));
- (void)arcToRadRect:(KMMYCUi_geometryRect *)rect startAngleRadians:(float)startAngleRadians sweepAngleRadians:(float)sweepAngleRadians forceMoveTo:(BOOL)forceMoveTo __attribute__((swift_name("arcToRad(rect:startAngleRadians:sweepAngleRadians:forceMoveTo:)")));
- (void)close __attribute__((swift_name("close()")));
- (void)cubicToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 x3:(float)x3 y3:(float)y3 __attribute__((swift_name("cubicTo(x1:y1:x2:y2:x3:y3:)")));
- (KMMYCUi_geometryRect *)getBounds __attribute__((swift_name("getBounds()")));
- (void)lineToX:(float)x y:(float)y __attribute__((swift_name("lineTo(x:y:)")));
- (void)moveToX:(float)x y:(float)y __attribute__((swift_name("moveTo(x:y:)")));
- (BOOL)opPath1:(id<KMMYCUi_graphicsPath>)path1 path2:(id<KMMYCUi_graphicsPath>)path2 operation:(int32_t)operation __attribute__((swift_name("op(path1:path2:operation:)")));
- (void)quadraticBezierToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 __attribute__((swift_name("quadraticBezierTo(x1:y1:x2:y2:)")));
- (void)relativeCubicToDx1:(float)dx1 dy1:(float)dy1 dx2:(float)dx2 dy2:(float)dy2 dx3:(float)dx3 dy3:(float)dy3 __attribute__((swift_name("relativeCubicTo(dx1:dy1:dx2:dy2:dx3:dy3:)")));
- (void)relativeLineToDx:(float)dx dy:(float)dy __attribute__((swift_name("relativeLineTo(dx:dy:)")));
- (void)relativeMoveToDx:(float)dx dy:(float)dy __attribute__((swift_name("relativeMoveTo(dx:dy:)")));
- (void)relativeQuadraticBezierToDx1:(float)dx1 dy1:(float)dy1 dx2:(float)dx2 dy2:(float)dy2 __attribute__((swift_name("relativeQuadraticBezierTo(dx1:dy1:dx2:dy2:)")));
- (void)reset __attribute__((swift_name("reset()")));
- (void)translateOffset:(int64_t)offset __attribute__((swift_name("translate(offset:)")));
@property int32_t fillType __attribute__((swift_name("fillType")));
@property (readonly) BOOL isConvex __attribute__((swift_name("isConvex")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinPair")))
@interface KMMYCKotlinPair<__covariant A, __covariant B> : KMMYCBase
- (instancetype)initWithFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("init(first:second:)"))) __attribute__((objc_designated_initializer));
- (KMMYCKotlinPair<A, B> *)doCopyFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("doCopy(first:second:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) A _Nullable first __attribute__((swift_name("first")));
@property (readonly) B _Nullable second __attribute__((swift_name("second")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinTriple")))
@interface KMMYCKotlinTriple<__covariant A, __covariant B, __covariant C> : KMMYCBase
- (instancetype)initWithFirst:(A _Nullable)first second:(B _Nullable)second third:(C _Nullable)third __attribute__((swift_name("init(first:second:third:)"))) __attribute__((objc_designated_initializer));
- (KMMYCKotlinTriple<A, B, C> *)doCopyFirst:(A _Nullable)first second:(B _Nullable)second third:(C _Nullable)third __attribute__((swift_name("doCopy(first:second:third:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) A _Nullable first __attribute__((swift_name("first")));
@property (readonly) B _Nullable second __attribute__((swift_name("second")));
@property (readonly) C _Nullable third __attribute__((swift_name("third")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_geometryRect")))
@interface KMMYCUi_geometryRect : KMMYCBase
- (instancetype)initWithLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("init(left:top:right:bottom:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_geometryRectCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)containsOffset:(int64_t)offset __attribute__((swift_name("contains(offset:)")));
- (KMMYCUi_geometryRect *)doCopyLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("doCopy(left:top:right:bottom:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_geometryRect *)deflateDelta:(float)delta __attribute__((swift_name("deflate(delta:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_geometryRect *)inflateDelta:(float)delta __attribute__((swift_name("inflate(delta:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_geometryRect *)intersectOther:(KMMYCUi_geometryRect *)other __attribute__((swift_name("intersect(other:)")));
- (BOOL)overlapsOther:(KMMYCUi_geometryRect *)other __attribute__((swift_name("overlaps(other:)")));
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_geometryRect *)translateOffset:(int64_t)offset __attribute__((swift_name("translate(offset:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_geometryRect *)translateTranslateX:(float)translateX translateY:(float)translateY __attribute__((swift_name("translate(translateX:translateY:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float bottom __attribute__((swift_name("bottom")));
@property (readonly) int64_t bottomCenter __attribute__((swift_name("bottomCenter")));
@property (readonly) int64_t bottomLeft __attribute__((swift_name("bottomLeft")));
@property (readonly) int64_t bottomRight __attribute__((swift_name("bottomRight")));
@property (readonly) int64_t center __attribute__((swift_name("center")));
@property (readonly) int64_t centerLeft __attribute__((swift_name("centerLeft")));
@property (readonly) int64_t centerRight __attribute__((swift_name("centerRight")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float height __attribute__((swift_name("height")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) BOOL isFinite __attribute__((swift_name("isFinite")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) BOOL isInfinite __attribute__((swift_name("isInfinite")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float left __attribute__((swift_name("left")));
@property (readonly) float maxDimension __attribute__((swift_name("maxDimension")));
@property (readonly) float minDimension __attribute__((swift_name("minDimension")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float right __attribute__((swift_name("right")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) int64_t size __attribute__((swift_name("size")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float top __attribute__((swift_name("top")));
@property (readonly) int64_t topCenter __attribute__((swift_name("topCenter")));
@property (readonly) int64_t topLeft __attribute__((swift_name("topLeft")));
@property (readonly) int64_t topRight __attribute__((swift_name("topRight")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float width __attribute__((swift_name("width")));
@end

__attribute__((swift_name("Ui_graphicsPaint")))
@protocol KMMYCUi_graphicsPaint
@required
- (KMMYCSkikoPaint *)asFrameworkPaint __attribute__((swift_name("asFrameworkPaint()")));
@property float alpha __attribute__((swift_name("alpha")));
@property int32_t blendMode __attribute__((swift_name("blendMode")));
@property uint64_t color __attribute__((swift_name("color")));
@property KMMYCUi_graphicsColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property int32_t filterQuality __attribute__((swift_name("filterQuality")));
@property BOOL isAntiAlias __attribute__((swift_name("isAntiAlias")));
@property id<KMMYCUi_graphicsPathEffect> _Nullable pathEffect __attribute__((swift_name("pathEffect")));
@property KMMYCSkikoShader * _Nullable shader __attribute__((swift_name("shader")));
@property int32_t strokeCap __attribute__((swift_name("strokeCap")));
@property int32_t strokeJoin __attribute__((swift_name("strokeJoin")));
@property float strokeMiterLimit __attribute__((swift_name("strokeMiterLimit")));
@property float strokeWidth __attribute__((swift_name("strokeWidth")));
@property int32_t style __attribute__((swift_name("style")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol KMMYCKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontWeight")))
@interface KMMYCUi_textFontWeight : KMMYCBase <KMMYCKotlinComparable>
- (instancetype)initWithWeight:(int32_t)weight __attribute__((swift_name("init(weight:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_textFontWeightCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(KMMYCUi_textFontWeight *)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t weight __attribute__((swift_name("weight")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((swift_name("Ui_textFontFamily")))
@interface KMMYCUi_textFontFamily : KMMYCBase
@property (class, readonly, getter=companion) KMMYCUi_textFontFamilyCompanion *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL canLoadSynchronously __attribute__((swift_name("canLoadSynchronously")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextGeometricTransform")))
@interface KMMYCUi_textTextGeometricTransform : KMMYCBase
- (instancetype)initWithScaleX:(float)scaleX skewX:(float)skewX __attribute__((swift_name("init(scaleX:skewX:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_textTextGeometricTransformCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCUi_textTextGeometricTransform *)doCopyScaleX:(float)scaleX skewX:(float)skewX __attribute__((swift_name("doCopy(scaleX:skewX:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float scaleX __attribute__((swift_name("scaleX")));
@property (readonly) float skewX __attribute__((swift_name("skewX")));
@end

__attribute__((swift_name("KotlinIterable")))
@protocol KMMYCKotlinIterable
@required
- (id<KMMYCKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
@end

__attribute__((swift_name("KotlinCollection")))
@protocol KMMYCKotlinCollection <KMMYCKotlinIterable>
@required
- (BOOL)containsElement:(id _Nullable)element __attribute__((swift_name("contains(element:)")));
- (BOOL)containsAllElements:(id)elements __attribute__((swift_name("containsAll(elements:)")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
@property (readonly) int32_t size_ __attribute__((swift_name("size_")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLocaleList")))
@interface KMMYCUi_textLocaleList : KMMYCBase <KMMYCKotlinCollection>
- (instancetype)initWithLanguageTags:(NSString *)languageTags __attribute__((swift_name("init(languageTags:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithLocales:(KMMYCKotlinArray<KMMYCUi_textLocale *> *)locales __attribute__((swift_name("init(locales:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithLocaleList:(NSArray<KMMYCUi_textLocale *> *)localeList __attribute__((swift_name("init(localeList:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_textLocaleListCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)containsElement:(KMMYCUi_textLocale *)element __attribute__((swift_name("contains(element:)")));
- (BOOL)containsAllElements:(id)elements __attribute__((swift_name("containsAll(elements:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (KMMYCUi_textLocale *)getI:(int32_t)i __attribute__((swift_name("get(i:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
- (id<KMMYCKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<KMMYCUi_textLocale *> *localeList __attribute__((swift_name("localeList")));
@property (readonly) int32_t size_ __attribute__((swift_name("size_")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextDecoration")))
@interface KMMYCUi_textTextDecoration : KMMYCBase
@property (class, readonly, getter=companion) KMMYCUi_textTextDecorationCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)containsOther:(KMMYCUi_textTextDecoration *)other __attribute__((swift_name("contains(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCUi_textTextDecoration *)plusDecoration:(KMMYCUi_textTextDecoration *)decoration __attribute__((swift_name("plus(decoration:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t mask __attribute__((swift_name("mask")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsShadow")))
@interface KMMYCUi_graphicsShadow : KMMYCBase
- (instancetype)initWithColor:(uint64_t)color offset:(int64_t)offset blurRadius:(float)blurRadius __attribute__((swift_name("init(color:offset:blurRadius:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_graphicsShadowCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCUi_graphicsShadow *)doCopyColor:(uint64_t)color offset:(int64_t)offset blurRadius:(float)blurRadius __attribute__((swift_name("doCopy(color:offset:blurRadius:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float blurRadius __attribute__((swift_name("blurRadius")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) uint64_t color __attribute__((swift_name("color")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) int64_t offset __attribute__((swift_name("offset")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextIndent")))
@interface KMMYCUi_textTextIndent : KMMYCBase
- (instancetype)initWithFirstLine:(int64_t)firstLine restLine:(int64_t)restLine __attribute__((swift_name("init(firstLine:restLine:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_textTextIndentCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCUi_textTextIndent *)doCopyFirstLine:(int64_t)firstLine restLine:(int64_t)restLine __attribute__((swift_name("doCopy(firstLine:restLine:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t firstLine __attribute__((swift_name("firstLine")));
@property (readonly) int64_t restLine __attribute__((swift_name("restLine")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformTextStyle")))
@interface KMMYCUi_textPlatformTextStyle : KMMYCBase
- (instancetype)initWithSpanStyle:(KMMYCUi_textPlatformSpanStyle * _Nullable)spanStyle paragraphStyle:(KMMYCUi_textPlatformParagraphStyle * _Nullable)paragraphStyle __attribute__((swift_name("init(spanStyle:paragraphStyle:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
@property (readonly) KMMYCUi_textPlatformParagraphStyle * _Nullable paragraphStyle __attribute__((swift_name("paragraphStyle")));
@property (readonly) KMMYCUi_textPlatformSpanStyle * _Nullable spanStyle __attribute__((swift_name("spanStyle")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLineHeightStyle")))
@interface KMMYCUi_textLineHeightStyle : KMMYCBase
- (instancetype)initWithAlignment:(float)alignment trim:(int32_t)trim __attribute__((swift_name("init(alignment:trim:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_textLineHeightStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alignment __attribute__((swift_name("alignment")));
@property (readonly) int32_t trim __attribute__((swift_name("trim")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLineBreak")))
@interface KMMYCUi_textLineBreak : KMMYCBase
@property (class, readonly, getter=companion) KMMYCUi_textLineBreakCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textHyphens")))
@interface KMMYCUi_textHyphens : KMMYCBase
@property (class, readonly, getter=companion) KMMYCUi_textHyphensCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextStyle.Companion")))
@interface KMMYCUi_textTextStyleCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textTextStyleCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textTextStyle *Default __attribute__((swift_name("Default")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textParagraphStyle")))
@interface KMMYCUi_textParagraphStyle : KMMYCBase
- (instancetype)initWithTextAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent __attribute__((swift_name("init(textAlign:textDirection:lineHeight:textIndent:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithTextAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformParagraphStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle __attribute__((swift_name("init(textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithTextAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformParagraphStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(KMMYCUi_textLineBreak * _Nullable)lineBreak hyphens:(KMMYCUi_textHyphens * _Nullable)hyphens __attribute__((swift_name("init(textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:)"))) __attribute__((objc_designated_initializer));
- (KMMYCUi_textParagraphStyle *)doCopyTextAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent __attribute__((swift_name("doCopy(textAlign:textDirection:lineHeight:textIndent:)")));
- (KMMYCUi_textParagraphStyle *)doCopyTextAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformParagraphStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle __attribute__((swift_name("doCopy(textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:)")));
- (KMMYCUi_textParagraphStyle *)doCopyTextAlign:(id _Nullable)textAlign textDirection:(id _Nullable)textDirection lineHeight:(int64_t)lineHeight textIndent:(KMMYCUi_textTextIndent * _Nullable)textIndent platformStyle:(KMMYCUi_textPlatformParagraphStyle * _Nullable)platformStyle lineHeightStyle:(KMMYCUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(KMMYCUi_textLineBreak * _Nullable)lineBreak hyphens:(KMMYCUi_textHyphens * _Nullable)hyphens __attribute__((swift_name("doCopy(textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textParagraphStyle *)mergeOther:(KMMYCUi_textParagraphStyle * _Nullable)other __attribute__((swift_name("merge(other:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textParagraphStyle *)plusOther:(KMMYCUi_textParagraphStyle *)other __attribute__((swift_name("plus(other:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCUi_textHyphens * _Nullable hyphens __attribute__((swift_name("hyphens")));
@property (readonly) KMMYCUi_textLineBreak * _Nullable lineBreak __attribute__((swift_name("lineBreak")));
@property (readonly) int64_t lineHeight __attribute__((swift_name("lineHeight")));
@property (readonly) KMMYCUi_textLineHeightStyle * _Nullable lineHeightStyle __attribute__((swift_name("lineHeightStyle")));
@property (readonly) KMMYCUi_textPlatformParagraphStyle * _Nullable platformStyle __attribute__((swift_name("platformStyle")));
@property (readonly) id _Nullable textAlign __attribute__((swift_name("textAlign")));
@property (readonly) id _Nullable textDirection __attribute__((swift_name("textDirection")));
@property (readonly) KMMYCUi_textTextIndent * _Nullable textIndent __attribute__((swift_name("textIndent")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textSpanStyle")))
@interface KMMYCUi_textSpanStyle : KMMYCBase
- (instancetype)initWithColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow __attribute__((swift_name("init(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow platformStyle:(KMMYCUi_textPlatformSpanStyle * _Nullable)platformStyle __attribute__((swift_name("init(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:platformStyle:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithBrush:(KMMYCUi_graphicsBrush * _Nullable)brush alpha:(float)alpha fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow platformStyle:(KMMYCUi_textPlatformSpanStyle * _Nullable)platformStyle __attribute__((swift_name("init(brush:alpha:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:platformStyle:)"))) __attribute__((objc_designated_initializer));
- (KMMYCUi_textSpanStyle *)doCopyBrush:(KMMYCUi_graphicsBrush * _Nullable)brush alpha:(float)alpha fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow platformStyle:(KMMYCUi_textPlatformSpanStyle * _Nullable)platformStyle __attribute__((swift_name("doCopy(brush:alpha:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:platformStyle:)")));
- (KMMYCUi_textSpanStyle *)doCopyColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow __attribute__((swift_name("doCopy(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:)")));
- (KMMYCUi_textSpanStyle *)doCopyColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(KMMYCUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(KMMYCUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(KMMYCUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(KMMYCUi_textTextDecoration * _Nullable)textDecoration shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow platformStyle:(KMMYCUi_textPlatformSpanStyle * _Nullable)platformStyle __attribute__((swift_name("doCopy(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:platformStyle:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textSpanStyle *)mergeOther:(KMMYCUi_textSpanStyle * _Nullable)other __attribute__((swift_name("merge(other:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textSpanStyle *)plusOther:(KMMYCUi_textSpanStyle *)other __attribute__((swift_name("plus(other:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) uint64_t background __attribute__((swift_name("background")));
@property (readonly) id _Nullable baselineShift __attribute__((swift_name("baselineShift")));
@property (readonly) KMMYCUi_graphicsBrush * _Nullable brush __attribute__((swift_name("brush")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) KMMYCUi_textFontFamily * _Nullable fontFamily __attribute__((swift_name("fontFamily")));
@property (readonly) NSString * _Nullable fontFeatureSettings __attribute__((swift_name("fontFeatureSettings")));
@property (readonly) int64_t fontSize __attribute__((swift_name("fontSize")));
@property (readonly) id _Nullable fontStyle __attribute__((swift_name("fontStyle")));
@property (readonly) id _Nullable fontSynthesis __attribute__((swift_name("fontSynthesis")));
@property (readonly) KMMYCUi_textFontWeight * _Nullable fontWeight __attribute__((swift_name("fontWeight")));
@property (readonly) int64_t letterSpacing __attribute__((swift_name("letterSpacing")));
@property (readonly) KMMYCUi_textLocaleList * _Nullable localeList __attribute__((swift_name("localeList")));
@property (readonly) KMMYCUi_textPlatformSpanStyle * _Nullable platformStyle __attribute__((swift_name("platformStyle")));
@property (readonly) KMMYCUi_graphicsShadow * _Nullable shadow __attribute__((swift_name("shadow")));
@property (readonly) KMMYCUi_textTextDecoration * _Nullable textDecoration __attribute__((swift_name("textDecoration")));
@property (readonly) KMMYCUi_textTextGeometricTransform * _Nullable textGeometricTransform __attribute__((swift_name("textGeometricTransform")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinIntArray")))
@interface KMMYCKotlinIntArray : KMMYCBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(KMMYCInt *(^)(KMMYCInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int32_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (KMMYCKotlinIntIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int32_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_unitLayoutDirection")))
@interface KMMYCUi_unitLayoutDirection : KMMYCKotlinEnum<KMMYCUi_unitLayoutDirection *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCUi_unitLayoutDirection *ltr __attribute__((swift_name("ltr")));
@property (class, readonly) KMMYCUi_unitLayoutDirection *rtl __attribute__((swift_name("rtl")));
+ (KMMYCKotlinArray<KMMYCUi_unitLayoutDirection *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsColorFilter.Companion")))
@interface KMMYCUi_graphicsColorFilterCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_graphicsColorFilterCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsColorFilter *)colorMatrixColorMatrix:(id)colorMatrix __attribute__((swift_name("colorMatrix(colorMatrix:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsColorFilter *)lightingMultiply:(uint64_t)multiply add:(uint64_t)add __attribute__((swift_name("lighting(multiply:add:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsColorFilter *)tintColor:(uint64_t)color blendMode:(int32_t)blendMode __attribute__((swift_name("tint(color:blendMode:)")));
@end

__attribute__((swift_name("Ui_graphicsImageBitmap")))
@protocol KMMYCUi_graphicsImageBitmap
@required
- (void)prepareToDraw __attribute__((swift_name("prepareToDraw()")));
- (void)readPixelsBuffer:(KMMYCKotlinIntArray *)buffer startX:(int32_t)startX startY:(int32_t)startY width:(int32_t)width height:(int32_t)height bufferOffset:(int32_t)bufferOffset stride:(int32_t)stride __attribute__((swift_name("readPixels(buffer:startX:startY:width:height:bufferOffset:stride:)")));
@property (readonly) KMMYCUi_graphicsColorSpace *colorSpace __attribute__((swift_name("colorSpace")));
@property (readonly) int32_t config __attribute__((swift_name("config")));
@property (readonly) BOOL hasAlpha __attribute__((swift_name("hasAlpha")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((swift_name("Ui_graphicsDrawContext")))
@protocol KMMYCUi_graphicsDrawContext
@required
@property (readonly) id<KMMYCUi_graphicsCanvas> canvas __attribute__((swift_name("canvas")));
@property int64_t size __attribute__((swift_name("size")));
@property (readonly) id<KMMYCUi_graphicsDrawTransform> transform __attribute__((swift_name("transform")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_unitDpRect")))
@interface KMMYCUi_unitDpRect : KMMYCBase
- (instancetype)initWithOrigin:(int64_t)origin size:(int64_t)size __attribute__((swift_name("init(origin:size:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("init(left:top:right:bottom:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_unitDpRectCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCUi_unitDpRect *)doCopyLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("doCopy(left:top:right:bottom:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float bottom __attribute__((swift_name("bottom")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float left __attribute__((swift_name("left")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float right __attribute__((swift_name("right")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float top __attribute__((swift_name("top")));
@end

__attribute__((swift_name("KotlinFloatIterator")))
@interface KMMYCKotlinFloatIterator : KMMYCBase <KMMYCKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (KMMYCFloat *)next __attribute__((swift_name("next()")));
- (float)nextFloat __attribute__((swift_name("nextFloat()")));
@end

__attribute__((swift_name("Ui_textFontFamilyResolver")))
@protocol KMMYCUi_textFontFamilyResolver
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)preloadFontFamily:(KMMYCUi_textFontFamily *)fontFamily completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("preload(fontFamily:completionHandler:)")));
- (id<KMMYCRuntimeState>)resolveFontFamily:(KMMYCUi_textFontFamily * _Nullable)fontFamily fontWeight:(KMMYCUi_textFontWeight *)fontWeight fontStyle:(int32_t)fontStyle fontSynthesis:(int32_t)fontSynthesis __attribute__((swift_name("resolve(fontFamily:fontWeight:fontStyle:fontSynthesis:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextLayoutResult")))
@interface KMMYCUi_textTextLayoutResult : KMMYCBase
- (instancetype)initWithLayoutInput:(KMMYCUi_textTextLayoutInput *)layoutInput multiParagraph:(KMMYCUi_textMultiParagraph *)multiParagraph size:(int64_t)size __attribute__((swift_name("init(layoutInput:multiParagraph:size:)"))) __attribute__((objc_designated_initializer));
- (KMMYCUi_textTextLayoutResult *)doCopyLayoutInput:(KMMYCUi_textTextLayoutInput *)layoutInput size:(int64_t)size __attribute__((swift_name("doCopy(layoutInput:size:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (KMMYCUi_textResolvedTextDirection *)getBidiRunDirectionOffset:(int32_t)offset __attribute__((swift_name("getBidiRunDirection(offset:)")));
- (KMMYCUi_geometryRect *)getBoundingBoxOffset:(int32_t)offset __attribute__((swift_name("getBoundingBox(offset:)")));
- (KMMYCUi_geometryRect *)getCursorRectOffset:(int32_t)offset __attribute__((swift_name("getCursorRect(offset:)")));
- (float)getHorizontalPositionOffset:(int32_t)offset usePrimaryDirection:(BOOL)usePrimaryDirection __attribute__((swift_name("getHorizontalPosition(offset:usePrimaryDirection:)")));
- (float)getLineBottomLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineBottom(lineIndex:)")));
- (int32_t)getLineEndLineIndex:(int32_t)lineIndex visibleEnd:(BOOL)visibleEnd __attribute__((swift_name("getLineEnd(lineIndex:visibleEnd:)")));
- (int32_t)getLineForOffsetOffset:(int32_t)offset __attribute__((swift_name("getLineForOffset(offset:)")));
- (int32_t)getLineForVerticalPositionVertical:(float)vertical __attribute__((swift_name("getLineForVerticalPosition(vertical:)")));
- (float)getLineLeftLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineLeft(lineIndex:)")));
- (float)getLineRightLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineRight(lineIndex:)")));
- (int32_t)getLineStartLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineStart(lineIndex:)")));
- (float)getLineTopLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineTop(lineIndex:)")));
- (int32_t)getOffsetForPositionPosition:(int64_t)position __attribute__((swift_name("getOffsetForPosition(position:)")));
- (KMMYCUi_textResolvedTextDirection *)getParagraphDirectionOffset:(int32_t)offset __attribute__((swift_name("getParagraphDirection(offset:)")));
- (id<KMMYCUi_graphicsPath>)getPathForRangeStart:(int32_t)start end:(int32_t)end __attribute__((swift_name("getPathForRange(start:end:)")));
- (int64_t)getWordBoundaryOffset:(int32_t)offset __attribute__((swift_name("getWordBoundary(offset:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isLineEllipsizedLineIndex:(int32_t)lineIndex __attribute__((swift_name("isLineEllipsized(lineIndex:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL didOverflowHeight __attribute__((swift_name("didOverflowHeight")));
@property (readonly) BOOL didOverflowWidth __attribute__((swift_name("didOverflowWidth")));
@property (readonly) float firstBaseline __attribute__((swift_name("firstBaseline")));
@property (readonly) BOOL hasVisualOverflow __attribute__((swift_name("hasVisualOverflow")));
@property (readonly) float lastBaseline __attribute__((swift_name("lastBaseline")));
@property (readonly) KMMYCUi_textTextLayoutInput *layoutInput __attribute__((swift_name("layoutInput")));
@property (readonly) int32_t lineCount __attribute__((swift_name("lineCount")));
@property (readonly) KMMYCUi_textMultiParagraph *multiParagraph __attribute__((swift_name("multiParagraph")));
@property (readonly) NSArray<id> *placeholderRects __attribute__((swift_name("placeholderRects")));
@property (readonly) int64_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("KotlinCharSequence")))
@protocol KMMYCKotlinCharSequence
@required
- (unichar)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id)subSequenceStartIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("subSequence(startIndex:endIndex:)")));
@property (readonly) int32_t length __attribute__((swift_name("length")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textAnnotatedString")))
@interface KMMYCUi_textAnnotatedString : KMMYCBase <KMMYCKotlinCharSequence>
- (instancetype)initWithText:(NSString *)text spanStyles:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textSpanStyle *> *> *)spanStyles paragraphStyles:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textParagraphStyle *> *> *)paragraphStyles __attribute__((swift_name("init(text:spanStyles:paragraphStyles:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (unichar)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (NSArray<KMMYCUi_textAnnotatedStringRange<NSString *> *> *)getStringAnnotationsStart:(int32_t)start end:(int32_t)end __attribute__((swift_name("getStringAnnotations(start:end:)")));
- (NSArray<KMMYCUi_textAnnotatedStringRange<NSString *> *> *)getStringAnnotationsTag:(NSString *)tag start:(int32_t)start end:(int32_t)end __attribute__((swift_name("getStringAnnotations(tag:start:end:)")));
- (NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textTtsAnnotation *> *> *)getTtsAnnotationsStart:(int32_t)start end:(int32_t)end __attribute__((swift_name("getTtsAnnotations(start:end:)")));
- (NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textUrlAnnotation *> *> *)getUrlAnnotationsStart:(int32_t)start end:(int32_t)end __attribute__((swift_name("getUrlAnnotations(start:end:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_textAnnotatedString *)plusOther:(KMMYCUi_textAnnotatedString *)other __attribute__((swift_name("plus(other:)")));
- (KMMYCUi_textAnnotatedString *)subSequenceRange:(int64_t)range __attribute__((swift_name("subSequence(range:)")));
- (KMMYCUi_textAnnotatedString *)subSequenceStartIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("subSequence(startIndex:endIndex:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t length __attribute__((swift_name("length")));
@property (readonly) NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textParagraphStyle *> *> *paragraphStyles __attribute__((swift_name("paragraphStyles")));
@property (readonly) NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textSpanStyle *> *> *spanStyles __attribute__((swift_name("spanStyles")));
@property (readonly) NSString *text __attribute__((swift_name("text")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlaceholder")))
@interface KMMYCUi_textPlaceholder : KMMYCBase
- (instancetype)initWithWidth:(int64_t)width height:(int64_t)height placeholderVerticalAlign:(int32_t)placeholderVerticalAlign __attribute__((swift_name("init(width:height:placeholderVerticalAlign:)"))) __attribute__((objc_designated_initializer));
- (KMMYCUi_textPlaceholder *)doCopyWidth:(int64_t)width height:(int64_t)height placeholderVerticalAlign:(int32_t)placeholderVerticalAlign __attribute__((swift_name("doCopy(width:height:placeholderVerticalAlign:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t height __attribute__((swift_name("height")));
@property (readonly) int32_t placeholderVerticalAlign __attribute__((swift_name("placeholderVerticalAlign")));
@property (readonly) int64_t width __attribute__((swift_name("width")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textAnnotatedStringRange")))
@interface KMMYCUi_textAnnotatedStringRange<T> : KMMYCBase
- (instancetype)initWithItem:(T _Nullable)item start:(int32_t)start end:(int32_t)end __attribute__((swift_name("init(item:start:end:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithItem:(T _Nullable)item start:(int32_t)start end:(int32_t)end tag:(NSString *)tag __attribute__((swift_name("init(item:start:end:tag:)"))) __attribute__((objc_designated_initializer));
- (KMMYCUi_textAnnotatedStringRange<T> *)doCopyItem:(T _Nullable)item start:(int32_t)start end:(int32_t)end tag:(NSString *)tag __attribute__((swift_name("doCopy(item:start:end:tag:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t end __attribute__((swift_name("end")));
@property (readonly) T _Nullable item __attribute__((swift_name("item")));
@property (readonly) int32_t start __attribute__((swift_name("start")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsBrush.Companion")))
@interface KMMYCUi_graphicsBrushCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_graphicsBrushCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)horizontalGradientColorStops:(KMMYCKotlinArray<KMMYCKotlinPair<KMMYCFloat *, id> *> *)colorStops startX:(float)startX endX:(float)endX tileMode:(int32_t)tileMode __attribute__((swift_name("horizontalGradient(colorStops:startX:endX:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)horizontalGradientColors:(NSArray<id> *)colors startX:(float)startX endX:(float)endX tileMode:(int32_t)tileMode __attribute__((swift_name("horizontalGradient(colors:startX:endX:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)linearGradientColorStops:(KMMYCKotlinArray<KMMYCKotlinPair<KMMYCFloat *, id> *> *)colorStops start:(int64_t)start end:(int64_t)end tileMode:(int32_t)tileMode __attribute__((swift_name("linearGradient(colorStops:start:end:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)linearGradientColors:(NSArray<id> *)colors start:(int64_t)start end:(int64_t)end tileMode:(int32_t)tileMode __attribute__((swift_name("linearGradient(colors:start:end:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)radialGradientColorStops:(KMMYCKotlinArray<KMMYCKotlinPair<KMMYCFloat *, id> *> *)colorStops center:(int64_t)center radius:(float)radius tileMode:(int32_t)tileMode __attribute__((swift_name("radialGradient(colorStops:center:radius:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)radialGradientColors:(NSArray<id> *)colors center:(int64_t)center radius:(float)radius tileMode:(int32_t)tileMode __attribute__((swift_name("radialGradient(colors:center:radius:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)sweepGradientColorStops:(KMMYCKotlinArray<KMMYCKotlinPair<KMMYCFloat *, id> *> *)colorStops center:(int64_t)center __attribute__((swift_name("sweepGradient(colorStops:center:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)sweepGradientColors:(NSArray<id> *)colors center:(int64_t)center __attribute__((swift_name("sweepGradient(colors:center:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)verticalGradientColorStops:(KMMYCKotlinArray<KMMYCKotlinPair<KMMYCFloat *, id> *> *)colorStops startY:(float)startY endY:(float)endY tileMode:(int32_t)tileMode __attribute__((swift_name("verticalGradient(colorStops:startY:endY:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (KMMYCUi_graphicsBrush *)verticalGradientColors:(NSArray<id> *)colors startY:(float)startY endY:(float)endY tileMode:(int32_t)tileMode __attribute__((swift_name("verticalGradient(colors:startY:endY:tileMode:)")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_geometryRoundRect")))
@interface KMMYCUi_geometryRoundRect : KMMYCBase
- (instancetype)initWithLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom topLeftCornerRadius:(int64_t)topLeftCornerRadius topRightCornerRadius:(int64_t)topRightCornerRadius bottomRightCornerRadius:(int64_t)bottomRightCornerRadius bottomLeftCornerRadius:(int64_t)bottomLeftCornerRadius __attribute__((swift_name("init(left:top:right:bottom:topLeftCornerRadius:topRightCornerRadius:bottomRightCornerRadius:bottomLeftCornerRadius:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_geometryRoundRectCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)containsPoint:(int64_t)point __attribute__((swift_name("contains(point:)")));
- (KMMYCUi_geometryRoundRect *)doCopyLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom topLeftCornerRadius:(int64_t)topLeftCornerRadius topRightCornerRadius:(int64_t)topRightCornerRadius bottomRightCornerRadius:(int64_t)bottomRightCornerRadius bottomLeftCornerRadius:(int64_t)bottomLeftCornerRadius __attribute__((swift_name("doCopy(left:top:right:bottom:topLeftCornerRadius:topRightCornerRadius:bottomRightCornerRadius:bottomLeftCornerRadius:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float bottom __attribute__((swift_name("bottom")));
@property (readonly) int64_t bottomLeftCornerRadius __attribute__((swift_name("bottomLeftCornerRadius")));
@property (readonly) int64_t bottomRightCornerRadius __attribute__((swift_name("bottomRightCornerRadius")));
@property (readonly) float height __attribute__((swift_name("height")));
@property (readonly) float left __attribute__((swift_name("left")));
@property (readonly) float right __attribute__((swift_name("right")));
@property (readonly) float top __attribute__((swift_name("top")));
@property (readonly) int64_t topLeftCornerRadius __attribute__((swift_name("topLeftCornerRadius")));
@property (readonly) int64_t topRightCornerRadius __attribute__((swift_name("topRightCornerRadius")));
@property (readonly) float width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_geometryRect.Companion")))
@interface KMMYCUi_geometryRectCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_geometryRectCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_geometryRect *Zero __attribute__((swift_name("Zero")));
@end

__attribute__((swift_name("SkikoNative")))
@interface KMMYCSkikoNative : KMMYCBase
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoNativeCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("SkikoManaged")))
@interface KMMYCSkikoManaged : KMMYCSkikoNative
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (void)close __attribute__((swift_name("close()")));
@property (readonly) BOOL isClosed __attribute__((swift_name("isClosed")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaint")))
@interface KMMYCSkikoPaint : KMMYCSkikoManaged
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoPaintCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoPath *)getFillPathSrc:(KMMYCSkikoPath *)src __attribute__((swift_name("getFillPath(src:)")));
- (KMMYCSkikoPath *)getFillPathSrc:(KMMYCSkikoPath *)src cull:(KMMYCSkikoRect * _Nullable)cull resScale:(float)resScale __attribute__((swift_name("getFillPath(src:cull:resScale:)")));
- (BOOL)hasNothingToDraw __attribute__((swift_name("hasNothingToDraw()")));
- (KMMYCSkikoPaint *)makeClone __attribute__((swift_name("makeClone()")));
- (KMMYCSkikoPaint *)reset __attribute__((swift_name("reset()")));
- (KMMYCSkikoPaint *)setARGBA:(int32_t)a r:(int32_t)r g:(int32_t)g b:(int32_t)b __attribute__((swift_name("setARGB(a:r:g:b:)")));
- (KMMYCSkikoPaint *)setAlphafA:(float)a __attribute__((swift_name("setAlphaf(a:)")));
- (KMMYCSkikoPaint *)setColor4fColor:(KMMYCSkikoColor4f *)color colorSpace:(KMMYCSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("setColor4f(color:colorSpace:)")));
- (KMMYCSkikoPaint *)setStrokeValue:(BOOL)value __attribute__((swift_name("setStroke(value:)")));
@property int32_t alpha __attribute__((swift_name("alpha")));
@property (readonly) float alphaf __attribute__((swift_name("alphaf")));
@property KMMYCSkikoBlendMode *blendMode __attribute__((swift_name("blendMode")));
@property int32_t color __attribute__((swift_name("color")));
@property KMMYCSkikoColor4f *color4f __attribute__((swift_name("color4f")));
@property KMMYCSkikoColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property KMMYCSkikoImageFilter * _Nullable imageFilter __attribute__((swift_name("imageFilter")));
@property BOOL isAntiAlias __attribute__((swift_name("isAntiAlias")));
@property BOOL isDither __attribute__((swift_name("isDither")));
@property (readonly) BOOL isSrcOver __attribute__((swift_name("isSrcOver")));
@property KMMYCSkikoMaskFilter * _Nullable maskFilter __attribute__((swift_name("maskFilter")));
@property KMMYCSkikoPaintMode *mode __attribute__((swift_name("mode")));
@property KMMYCSkikoPathEffect * _Nullable pathEffect __attribute__((swift_name("pathEffect")));
@property KMMYCSkikoShader * _Nullable shader __attribute__((swift_name("shader")));
@property KMMYCSkikoPaintStrokeCap *strokeCap __attribute__((swift_name("strokeCap")));
@property KMMYCSkikoPaintStrokeJoin *strokeJoin __attribute__((swift_name("strokeJoin")));
@property float strokeMiter __attribute__((swift_name("strokeMiter")));
@property float strokeWidth __attribute__((swift_name("strokeWidth")));
@end

__attribute__((swift_name("SkikoRefCnt")))
@interface KMMYCSkikoRefCnt : KMMYCSkikoManaged

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t refCount __attribute__((swift_name("refCount")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoShader")))
@interface KMMYCSkikoShader : KMMYCSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoShaderCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoShader *)makeWithColorFilterF:(KMMYCSkikoColorFilter * _Nullable)f __attribute__((swift_name("makeWithColorFilter(f:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontWeight.Companion")))
@interface KMMYCUi_textFontWeightCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textFontWeightCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *Black __attribute__((swift_name("Black")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *Bold __attribute__((swift_name("Bold")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *ExtraBold __attribute__((swift_name("ExtraBold")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *ExtraLight __attribute__((swift_name("ExtraLight")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *Light __attribute__((swift_name("Light")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *Medium __attribute__((swift_name("Medium")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *Normal __attribute__((swift_name("Normal")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *SemiBold __attribute__((swift_name("SemiBold")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *Thin __attribute__((swift_name("Thin")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W100 __attribute__((swift_name("W100")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W200 __attribute__((swift_name("W200")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W300 __attribute__((swift_name("W300")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W400 __attribute__((swift_name("W400")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W500 __attribute__((swift_name("W500")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W600 __attribute__((swift_name("W600")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W700 __attribute__((swift_name("W700")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W800 __attribute__((swift_name("W800")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textFontWeight *W900 __attribute__((swift_name("W900")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontFamily.Companion")))
@interface KMMYCUi_textFontFamilyCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textFontFamilyCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCUi_textGenericFontFamily *Cursive __attribute__((swift_name("Cursive")));
@property (readonly) KMMYCUi_textSystemFontFamily *Default __attribute__((swift_name("Default")));
@property (readonly) KMMYCUi_textGenericFontFamily *Monospace __attribute__((swift_name("Monospace")));
@property (readonly) KMMYCUi_textGenericFontFamily *SansSerif __attribute__((swift_name("SansSerif")));
@property (readonly) KMMYCUi_textGenericFontFamily *Serif __attribute__((swift_name("Serif")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextGeometricTransform.Companion")))
@interface KMMYCUi_textTextGeometricTransformCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textTextGeometricTransformCompanion *shared __attribute__((swift_name("shared")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLocale")))
@interface KMMYCUi_textLocale : KMMYCBase
- (instancetype)initWithLanguageTag:(NSString *)languageTag __attribute__((swift_name("init(languageTag:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCUi_textLocaleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)toLanguageTag __attribute__((swift_name("toLanguageTag()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *language __attribute__((swift_name("language")));
@property (readonly) NSString *region __attribute__((swift_name("region")));
@property (readonly) NSString *script __attribute__((swift_name("script")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLocaleList.Companion")))
@interface KMMYCUi_textLocaleListCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textLocaleListCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCUi_textLocaleList *current __attribute__((swift_name("current")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextDecoration.Companion")))
@interface KMMYCUi_textTextDecorationCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textTextDecorationCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCUi_textTextDecoration *)combineDecorations:(NSArray<KMMYCUi_textTextDecoration *> *)decorations __attribute__((swift_name("combine(decorations:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textTextDecoration *LineThrough __attribute__((swift_name("LineThrough")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textTextDecoration *None __attribute__((swift_name("None")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textTextDecoration *Underline __attribute__((swift_name("Underline")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsShadow.Companion")))
@interface KMMYCUi_graphicsShadowCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_graphicsShadowCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_graphicsShadow *None __attribute__((swift_name("None")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextIndent.Companion")))
@interface KMMYCUi_textTextIndentCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textTextIndentCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) KMMYCUi_textTextIndent *None __attribute__((swift_name("None")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformSpanStyle")))
@interface KMMYCUi_textPlatformSpanStyle : KMMYCBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) KMMYCUi_textPlatformSpanStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCUi_textPlatformSpanStyle *)mergeOther:(KMMYCUi_textPlatformSpanStyle * _Nullable)other __attribute__((swift_name("merge(other:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformParagraphStyle")))
@interface KMMYCUi_textPlatformParagraphStyle : KMMYCBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) KMMYCUi_textPlatformParagraphStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCUi_textPlatformParagraphStyle *)mergeOther:(KMMYCUi_textPlatformParagraphStyle * _Nullable)other __attribute__((swift_name("merge(other:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLineHeightStyle.Companion")))
@interface KMMYCUi_textLineHeightStyleCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textLineHeightStyleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCUi_textLineHeightStyle *Default __attribute__((swift_name("Default")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLineBreak.Companion")))
@interface KMMYCUi_textLineBreakCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textLineBreakCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCUi_textLineBreak *Heading __attribute__((swift_name("Heading")));
@property (readonly) KMMYCUi_textLineBreak *Paragraph __attribute__((swift_name("Paragraph")));
@property (readonly) KMMYCUi_textLineBreak *Simple __attribute__((swift_name("Simple")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textHyphens.Companion")))
@interface KMMYCUi_textHyphensCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textHyphensCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCUi_textHyphens *Auto __attribute__((swift_name("Auto")));
@property (readonly) KMMYCUi_textHyphens *None __attribute__((swift_name("None")));
@end

__attribute__((swift_name("KotlinIntIterator")))
@interface KMMYCKotlinIntIterator : KMMYCBase <KMMYCKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (KMMYCInt *)next __attribute__((swift_name("next()")));
- (int32_t)nextInt __attribute__((swift_name("nextInt()")));
@end

__attribute__((swift_name("Ui_graphicsColorSpace")))
@interface KMMYCUi_graphicsColorSpace : KMMYCBase
- (instancetype)initWithName:(NSString *)name model:(int64_t)model __attribute__((swift_name("init(name:model:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (KMMYCKotlinFloatArray *)fromXyzX:(float)x y:(float)y z:(float)z __attribute__((swift_name("fromXyz(x:y:z:)")));
- (KMMYCKotlinFloatArray *)fromXyzV:(KMMYCKotlinFloatArray *)v __attribute__((swift_name("fromXyz(v:)")));
- (float)getMaxValueComponent:(int32_t)component __attribute__((swift_name("getMaxValue(component:)")));
- (float)getMinValueComponent:(int32_t)component __attribute__((swift_name("getMinValue(component:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (KMMYCKotlinFloatArray *)toXyzR:(float)r g:(float)g b:(float)b __attribute__((swift_name("toXyz(r:g:b:)")));
- (KMMYCKotlinFloatArray *)toXyzV:(KMMYCKotlinFloatArray *)v __attribute__((swift_name("toXyz(v:)")));
@property (readonly) int32_t componentCount __attribute__((swift_name("componentCount")));
@property (readonly) BOOL isSrgb __attribute__((swift_name("isSrgb")));
@property (readonly) BOOL isWideGamut __attribute__((swift_name("isWideGamut")));
@property (readonly) int64_t model __attribute__((swift_name("model")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((swift_name("Ui_graphicsCanvas")))
@protocol KMMYCUi_graphicsCanvas
@required
- (void)clipPathPath:(id<KMMYCUi_graphicsPath>)path clipOp:(int32_t)clipOp __attribute__((swift_name("clipPath(path:clipOp:)")));
- (void)clipRectRect:(KMMYCUi_geometryRect *)rect clipOp:(int32_t)clipOp __attribute__((swift_name("clipRect(rect:clipOp:)")));
- (void)clipRectLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom clipOp:(int32_t)clipOp __attribute__((swift_name("clipRect(left:top:right:bottom:clipOp:)")));
- (void)concatMatrix:(id)matrix __attribute__((swift_name("concat(matrix:)")));
- (void)disableZ __attribute__((swift_name("disableZ()")));
- (void)drawArcRect:(KMMYCUi_geometryRect *)rect startAngle:(float)startAngle sweepAngle:(float)sweepAngle useCenter:(BOOL)useCenter paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawArc(rect:startAngle:sweepAngle:useCenter:paint:)")));
- (void)drawArcLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom startAngle:(float)startAngle sweepAngle:(float)sweepAngle useCenter:(BOOL)useCenter paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawArc(left:top:right:bottom:startAngle:sweepAngle:useCenter:paint:)")));
- (void)drawArcRadRect:(KMMYCUi_geometryRect *)rect startAngleRad:(float)startAngleRad sweepAngleRad:(float)sweepAngleRad useCenter:(BOOL)useCenter paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawArcRad(rect:startAngleRad:sweepAngleRad:useCenter:paint:)")));
- (void)drawCircleCenter:(int64_t)center radius:(float)radius paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawCircle(center:radius:paint:)")));
- (void)drawImageImage:(id<KMMYCUi_graphicsImageBitmap>)image topLeftOffset:(int64_t)topLeftOffset paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawImage(image:topLeftOffset:paint:)")));
- (void)drawImageRectImage:(id<KMMYCUi_graphicsImageBitmap>)image srcOffset:(int64_t)srcOffset srcSize:(int64_t)srcSize dstOffset:(int64_t)dstOffset dstSize:(int64_t)dstSize paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawImageRect(image:srcOffset:srcSize:dstOffset:dstSize:paint:)")));
- (void)drawLineP1:(int64_t)p1 p2:(int64_t)p2 paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawLine(p1:p2:paint:)")));
- (void)drawOvalRect:(KMMYCUi_geometryRect *)rect paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawOval(rect:paint:)")));
- (void)drawOvalLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawOval(left:top:right:bottom:paint:)")));
- (void)drawPathPath:(id<KMMYCUi_graphicsPath>)path paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawPath(path:paint:)")));
- (void)drawPointsPointMode:(int32_t)pointMode points:(NSArray<id> *)points paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawPoints(pointMode:points:paint:)")));
- (void)drawRawPointsPointMode:(int32_t)pointMode points:(KMMYCKotlinFloatArray *)points paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawRawPoints(pointMode:points:paint:)")));
- (void)drawRectRect:(KMMYCUi_geometryRect *)rect paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawRect(rect:paint:)")));
- (void)drawRectLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawRect(left:top:right:bottom:paint:)")));
- (void)drawRoundRectLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom radiusX:(float)radiusX radiusY:(float)radiusY paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawRoundRect(left:top:right:bottom:radiusX:radiusY:paint:)")));
- (void)drawVerticesVertices:(KMMYCUi_graphicsVertices *)vertices blendMode:(int32_t)blendMode paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("drawVertices(vertices:blendMode:paint:)")));
- (void)enableZ __attribute__((swift_name("enableZ()")));
- (void)restore __attribute__((swift_name("restore()")));
- (void)rotateDegrees:(float)degrees __attribute__((swift_name("rotate(degrees:)")));
- (void)save __attribute__((swift_name("save()")));
- (void)saveLayerBounds:(KMMYCUi_geometryRect *)bounds paint:(id<KMMYCUi_graphicsPaint>)paint __attribute__((swift_name("saveLayer(bounds:paint:)")));
- (void)scaleSx:(float)sx sy:(float)sy __attribute__((swift_name("scale(sx:sy:)")));
- (void)skewSx:(float)sx sy:(float)sy __attribute__((swift_name("skew(sx:sy:)")));
- (void)skewRadSxRad:(float)sxRad syRad:(float)syRad __attribute__((swift_name("skewRad(sxRad:syRad:)")));
- (void)translateDx:(float)dx dy:(float)dy __attribute__((swift_name("translate(dx:dy:)")));
@end

__attribute__((swift_name("Ui_graphicsDrawTransform")))
@protocol KMMYCUi_graphicsDrawTransform
@required
- (void)clipPathPath:(id<KMMYCUi_graphicsPath>)path clipOp:(int32_t)clipOp __attribute__((swift_name("clipPath(path:clipOp:)")));
- (void)clipRectLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom clipOp:(int32_t)clipOp __attribute__((swift_name("clipRect(left:top:right:bottom:clipOp:)")));
- (void)insetLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("inset(left:top:right:bottom:)")));
- (void)rotateDegrees:(float)degrees pivot:(int64_t)pivot __attribute__((swift_name("rotate(degrees:pivot:)")));
- (void)scaleScaleX:(float)scaleX scaleY:(float)scaleY pivot:(int64_t)pivot __attribute__((swift_name("scale(scaleX:scaleY:pivot:)")));
- (void)transformMatrix:(id)matrix __attribute__((swift_name("transform(matrix:)")));
- (void)translateLeft:(float)left top:(float)top __attribute__((swift_name("translate(left:top:)")));
@property (readonly) int64_t center __attribute__((swift_name("center")));
@property (readonly) int64_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_unitDpRect.Companion")))
@interface KMMYCUi_unitDpRectCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_unitDpRectCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface KMMYCKotlinThrowable : KMMYCBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (KMMYCKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface KMMYCKotlinException : KMMYCKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface KMMYCKotlinRuntimeException : KMMYCKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface KMMYCKotlinIllegalStateException : KMMYCKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface KMMYCKotlinCancellationException : KMMYCKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KMMYCKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
__attribute__((swift_name("RuntimeState")))
@protocol KMMYCRuntimeState
@required
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextLayoutInput")))
@interface KMMYCUi_textTextLayoutInput : KMMYCBase
- (instancetype)initWithText:(KMMYCUi_textAnnotatedString *)text style:(KMMYCUi_textTextStyle *)style placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders maxLines:(int32_t)maxLines softWrap:(BOOL)softWrap overflow:(int32_t)overflow density:(id<KMMYCUi_unitDensity>)density layoutDirection:(KMMYCUi_unitLayoutDirection *)layoutDirection resourceLoader:(id<KMMYCUi_textFontResourceLoader>)resourceLoader constraints:(int64_t)constraints __attribute__((swift_name("init(text:style:placeholders:maxLines:softWrap:overflow:density:layoutDirection:resourceLoader:constraints:)"))) __attribute__((objc_designated_initializer)) __attribute__((deprecated("Font.ResourceLoader is replaced with FontFamily.Resolver")));
- (instancetype)initWithText:(KMMYCUi_textAnnotatedString *)text style:(KMMYCUi_textTextStyle *)style placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders maxLines:(int32_t)maxLines softWrap:(BOOL)softWrap overflow:(int32_t)overflow density:(id<KMMYCUi_unitDensity>)density layoutDirection:(KMMYCUi_unitLayoutDirection *)layoutDirection fontFamilyResolver:(id<KMMYCUi_textFontFamilyResolver>)fontFamilyResolver constraints:(int64_t)constraints __attribute__((swift_name("init(text:style:placeholders:maxLines:softWrap:overflow:density:layoutDirection:fontFamilyResolver:constraints:)"))) __attribute__((objc_designated_initializer));
- (KMMYCUi_textTextLayoutInput *)doCopyText:(KMMYCUi_textAnnotatedString *)text style:(KMMYCUi_textTextStyle *)style placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders maxLines:(int32_t)maxLines softWrap:(BOOL)softWrap overflow:(int32_t)overflow density:(id<KMMYCUi_unitDensity>)density layoutDirection:(KMMYCUi_unitLayoutDirection *)layoutDirection resourceLoader:(id<KMMYCUi_textFontResourceLoader>)resourceLoader constraints:(int64_t)constraints __attribute__((swift_name("doCopy(text:style:placeholders:maxLines:softWrap:overflow:density:layoutDirection:resourceLoader:constraints:)"))) __attribute__((deprecated("Font.ResourceLoader is deprecated")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t constraints __attribute__((swift_name("constraints")));
@property (readonly) id<KMMYCUi_unitDensity> density __attribute__((swift_name("density")));
@property (readonly) id<KMMYCUi_textFontFamilyResolver> fontFamilyResolver __attribute__((swift_name("fontFamilyResolver")));
@property (readonly) KMMYCUi_unitLayoutDirection *layoutDirection __attribute__((swift_name("layoutDirection")));
@property (readonly) int32_t maxLines __attribute__((swift_name("maxLines")));
@property (readonly) int32_t overflow __attribute__((swift_name("overflow")));
@property (readonly) NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *placeholders __attribute__((swift_name("placeholders")));
@property (readonly) id<KMMYCUi_textFontResourceLoader> resourceLoader __attribute__((swift_name("resourceLoader"))) __attribute__((deprecated("Replaced with FontFamily.Resolver")));
@property (readonly) BOOL softWrap __attribute__((swift_name("softWrap")));
@property (readonly) KMMYCUi_textTextStyle *style __attribute__((swift_name("style")));
@property (readonly) KMMYCUi_textAnnotatedString *text __attribute__((swift_name("text")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textMultiParagraph")))
@interface KMMYCUi_textMultiParagraph : KMMYCBase
- (instancetype)initWithIntrinsics:(KMMYCUi_textMultiParagraphIntrinsics *)intrinsics maxLines:(int32_t)maxLines ellipsis:(BOOL)ellipsis width:(float)width __attribute__((swift_name("init(intrinsics:maxLines:ellipsis:width:)"))) __attribute__((objc_designated_initializer)) __attribute__((deprecated("MultiParagraph that takes maximum allowed width is deprecated, pass constraints instead.")));
- (instancetype)initWithAnnotatedString:(KMMYCUi_textAnnotatedString *)annotatedString style:(KMMYCUi_textTextStyle *)style placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders maxLines:(int32_t)maxLines ellipsis:(BOOL)ellipsis width:(float)width density:(id<KMMYCUi_unitDensity>)density resourceLoader:(id<KMMYCUi_textFontResourceLoader>)resourceLoader __attribute__((swift_name("init(annotatedString:style:placeholders:maxLines:ellipsis:width:density:resourceLoader:)"))) __attribute__((objc_designated_initializer)) __attribute__((deprecated("Font.ResourceLoader is deprecated, use fontFamilyResolver instead")));
- (instancetype)initWithAnnotatedString:(KMMYCUi_textAnnotatedString *)annotatedString style:(KMMYCUi_textTextStyle *)style width:(float)width density:(id<KMMYCUi_unitDensity>)density fontFamilyResolver:(id<KMMYCUi_textFontFamilyResolver>)fontFamilyResolver placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders maxLines:(int32_t)maxLines ellipsis:(BOOL)ellipsis __attribute__((swift_name("init(annotatedString:style:width:density:fontFamilyResolver:placeholders:maxLines:ellipsis:)"))) __attribute__((objc_designated_initializer)) __attribute__((deprecated("MultiParagraph that takes maximum allowed width is deprecated, pass constraints instead.")));
- (instancetype)initWithAnnotatedString:(KMMYCUi_textAnnotatedString *)annotatedString style:(KMMYCUi_textTextStyle *)style constraints:(int64_t)constraints density:(id<KMMYCUi_unitDensity>)density fontFamilyResolver:(id<KMMYCUi_textFontFamilyResolver>)fontFamilyResolver placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders maxLines:(int32_t)maxLines ellipsis:(BOOL)ellipsis __attribute__((swift_name("init(annotatedString:style:constraints:density:fontFamilyResolver:placeholders:maxLines:ellipsis:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithIntrinsics:(KMMYCUi_textMultiParagraphIntrinsics *)intrinsics constraints:(int64_t)constraints maxLines:(int32_t)maxLines ellipsis:(BOOL)ellipsis __attribute__((swift_name("init(intrinsics:constraints:maxLines:ellipsis:)"))) __attribute__((objc_designated_initializer));
- (KMMYCUi_textResolvedTextDirection *)getBidiRunDirectionOffset:(int32_t)offset __attribute__((swift_name("getBidiRunDirection(offset:)")));
- (KMMYCUi_geometryRect *)getBoundingBoxOffset:(int32_t)offset __attribute__((swift_name("getBoundingBox(offset:)")));
- (KMMYCUi_geometryRect *)getCursorRectOffset:(int32_t)offset __attribute__((swift_name("getCursorRect(offset:)")));
- (float)getHorizontalPositionOffset:(int32_t)offset usePrimaryDirection:(BOOL)usePrimaryDirection __attribute__((swift_name("getHorizontalPosition(offset:usePrimaryDirection:)")));
- (float)getLineBottomLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineBottom(lineIndex:)")));
- (int32_t)getLineEndLineIndex:(int32_t)lineIndex visibleEnd:(BOOL)visibleEnd __attribute__((swift_name("getLineEnd(lineIndex:visibleEnd:)")));
- (int32_t)getLineForOffsetOffset:(int32_t)offset __attribute__((swift_name("getLineForOffset(offset:)")));
- (int32_t)getLineForVerticalPositionVertical:(float)vertical __attribute__((swift_name("getLineForVerticalPosition(vertical:)")));
- (float)getLineHeightLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineHeight(lineIndex:)")));
- (float)getLineLeftLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineLeft(lineIndex:)")));
- (float)getLineRightLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineRight(lineIndex:)")));
- (int32_t)getLineStartLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineStart(lineIndex:)")));
- (float)getLineTopLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineTop(lineIndex:)")));
- (float)getLineWidthLineIndex:(int32_t)lineIndex __attribute__((swift_name("getLineWidth(lineIndex:)")));
- (int32_t)getOffsetForPositionPosition:(int64_t)position __attribute__((swift_name("getOffsetForPosition(position:)")));
- (KMMYCUi_textResolvedTextDirection *)getParagraphDirectionOffset:(int32_t)offset __attribute__((swift_name("getParagraphDirection(offset:)")));
- (id<KMMYCUi_graphicsPath>)getPathForRangeStart:(int32_t)start end:(int32_t)end __attribute__((swift_name("getPathForRange(start:end:)")));
- (int64_t)getWordBoundaryOffset:(int32_t)offset __attribute__((swift_name("getWordBoundary(offset:)")));
- (BOOL)isLineEllipsizedLineIndex:(int32_t)lineIndex __attribute__((swift_name("isLineEllipsized(lineIndex:)")));
- (void)paintCanvas:(id<KMMYCUi_graphicsCanvas>)canvas brush:(KMMYCUi_graphicsBrush *)brush alpha:(float)alpha shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow decoration:(KMMYCUi_textTextDecoration * _Nullable)decoration __attribute__((swift_name("paint(canvas:brush:alpha:shadow:decoration:)")));
- (void)paintCanvas:(id<KMMYCUi_graphicsCanvas>)canvas color:(uint64_t)color shadow:(KMMYCUi_graphicsShadow * _Nullable)shadow decoration:(KMMYCUi_textTextDecoration * _Nullable)decoration __attribute__((swift_name("paint(canvas:color:shadow:decoration:)")));
@property (readonly) BOOL didExceedMaxLines __attribute__((swift_name("didExceedMaxLines")));
@property (readonly) float firstBaseline __attribute__((swift_name("firstBaseline")));
@property (readonly) float height __attribute__((swift_name("height")));
@property (readonly) KMMYCUi_textMultiParagraphIntrinsics *intrinsics __attribute__((swift_name("intrinsics")));
@property (readonly) float lastBaseline __attribute__((swift_name("lastBaseline")));
@property (readonly) int32_t lineCount __attribute__((swift_name("lineCount")));
@property (readonly) float maxIntrinsicWidth __attribute__((swift_name("maxIntrinsicWidth")));
@property (readonly) int32_t maxLines __attribute__((swift_name("maxLines")));
@property (readonly) float minIntrinsicWidth __attribute__((swift_name("minIntrinsicWidth")));
@property (readonly) NSArray<id> *placeholderRects __attribute__((swift_name("placeholderRects")));
@property (readonly) float width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textResolvedTextDirection")))
@interface KMMYCUi_textResolvedTextDirection : KMMYCKotlinEnum<KMMYCUi_textResolvedTextDirection *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCUi_textResolvedTextDirection *ltr __attribute__((swift_name("ltr")));
@property (class, readonly) KMMYCUi_textResolvedTextDirection *rtl __attribute__((swift_name("rtl")));
+ (KMMYCKotlinArray<KMMYCUi_textResolvedTextDirection *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((swift_name("Ui_textTtsAnnotation")))
@interface KMMYCUi_textTtsAnnotation : KMMYCBase
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textUrlAnnotation")))
@interface KMMYCUi_textUrlAnnotation : KMMYCBase
- (instancetype)initWithUrl:(NSString *)url __attribute__((swift_name("init(url:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *url __attribute__((swift_name("url")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_geometryRoundRect.Companion")))
@interface KMMYCUi_geometryRoundRectCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_geometryRoundRectCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
@property (readonly) KMMYCUi_geometryRoundRect *Zero __attribute__((swift_name("Zero")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoNative.Companion")))
@interface KMMYCSkikoNativeCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoNativeCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) void * _Nullable NullPointer __attribute__((swift_name("NullPointer")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaint.Companion")))
@interface KMMYCSkikoPaintCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoPaintCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPath")))
@interface KMMYCSkikoPath : KMMYCSkikoManaged <KMMYCKotlinIterable>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoPathCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoPath *)addArcOval:(KMMYCSkikoRect *)oval startAngle:(float)startAngle sweepAngle:(float)sweepAngle __attribute__((swift_name("addArc(oval:startAngle:sweepAngle:)")));
- (KMMYCSkikoPath *)addCircleX:(float)x y:(float)y radius:(float)radius dir:(KMMYCSkikoPathDirection *)dir __attribute__((swift_name("addCircle(x:y:radius:dir:)")));
- (KMMYCSkikoPath *)addOvalOval:(KMMYCSkikoRect *)oval dir:(KMMYCSkikoPathDirection *)dir start:(int32_t)start __attribute__((swift_name("addOval(oval:dir:start:)")));
- (KMMYCSkikoPath *)addPathSrc:(KMMYCSkikoPath * _Nullable)src extend:(BOOL)extend __attribute__((swift_name("addPath(src:extend:)")));
- (KMMYCSkikoPath *)addPathSrc:(KMMYCSkikoPath * _Nullable)src dx:(float)dx dy:(float)dy extend:(BOOL)extend __attribute__((swift_name("addPath(src:dx:dy:extend:)")));
- (KMMYCSkikoPath *)addPathSrc:(KMMYCSkikoPath * _Nullable)src matrix:(KMMYCSkikoMatrix33 *)matrix extend:(BOOL)extend __attribute__((swift_name("addPath(src:matrix:extend:)")));
- (KMMYCSkikoPath *)addPolyPts:(KMMYCKotlinArray<KMMYCSkikoPoint *> *)pts close:(BOOL)close __attribute__((swift_name("addPoly(pts:close:)")));
- (KMMYCSkikoPath *)addPolyPts:(KMMYCKotlinFloatArray *)pts close_:(BOOL)close __attribute__((swift_name("addPoly(pts:close_:)")));
- (KMMYCSkikoPath *)addRRectRrect:(KMMYCSkikoRRect *)rrect dir:(KMMYCSkikoPathDirection *)dir start:(int32_t)start __attribute__((swift_name("addRRect(rrect:dir:start:)")));
- (KMMYCSkikoPath *)addRectRect:(KMMYCSkikoRect *)rect dir:(KMMYCSkikoPathDirection *)dir start:(int32_t)start __attribute__((swift_name("addRect(rect:dir:start:)")));
- (KMMYCSkikoPath *)arcToOval:(KMMYCSkikoRect *)oval startAngle:(float)startAngle sweepAngle:(float)sweepAngle forceMoveTo:(BOOL)forceMoveTo __attribute__((swift_name("arcTo(oval:startAngle:sweepAngle:forceMoveTo:)")));
- (KMMYCSkikoPath *)closePath __attribute__((swift_name("closePath()")));
- (KMMYCSkikoRect *)computeTightBounds __attribute__((swift_name("computeTightBounds()")));
- (KMMYCSkikoPath *)conicToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 w:(float)w __attribute__((swift_name("conicTo(x1:y1:x2:y2:w:)")));
- (KMMYCSkikoPath *)conicToP1:(KMMYCSkikoPoint *)p1 p2:(KMMYCSkikoPoint *)p2 w:(float)w __attribute__((swift_name("conicTo(p1:p2:w:)")));
- (BOOL)conservativelyContainsRectRect:(KMMYCSkikoRect *)rect __attribute__((swift_name("conservativelyContainsRect(rect:)")));
- (BOOL)containsX:(float)x y:(float)y __attribute__((swift_name("contains(x:y:)")));
- (BOOL)containsP:(KMMYCSkikoPoint *)p __attribute__((swift_name("contains(p:)")));
- (KMMYCSkikoPath *)cubicToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 x3:(float)x3 y3:(float)y3 __attribute__((swift_name("cubicTo(x1:y1:x2:y2:x3:y3:)")));
- (KMMYCSkikoPath *)cubicToP1:(KMMYCSkikoPoint *)p1 p2:(KMMYCSkikoPoint *)p2 p3:(KMMYCSkikoPoint *)p3 __attribute__((swift_name("cubicTo(p1:p2:p3:)")));
- (KMMYCSkikoPath *)dump __attribute__((swift_name("dump()")));
- (KMMYCSkikoPath *)dumpHex __attribute__((swift_name("dumpHex()")));
- (KMMYCSkikoPath *)ellipticalArcToRx:(float)rx ry:(float)ry xAxisRotate:(float)xAxisRotate arc:(KMMYCSkikoPathEllipseArc *)arc direction:(KMMYCSkikoPathDirection *)direction x:(float)x y:(float)y __attribute__((swift_name("ellipticalArcTo(rx:ry:xAxisRotate:arc:direction:x:y:)")));
- (KMMYCSkikoPath *)ellipticalArcToR:(KMMYCSkikoPoint *)r xAxisRotate:(float)xAxisRotate arc:(KMMYCSkikoPathEllipseArc *)arc direction:(KMMYCSkikoPathDirection *)direction xy:(KMMYCSkikoPoint *)xy __attribute__((swift_name("ellipticalArcTo(r:xAxisRotate:arc:direction:xy:)")));
- (KMMYCSkikoPoint *)getPointIndex:(int32_t)index __attribute__((swift_name("getPoint(index:)")));
- (int32_t)getPointsPoints:(KMMYCKotlinArray<KMMYCSkikoPoint *> * _Nullable)points max:(int32_t)max __attribute__((swift_name("getPoints(points:max:)")));
- (int32_t)getVerbsVerbs:(KMMYCKotlinArray<KMMYCSkikoPathVerb *> * _Nullable)verbs max:(int32_t)max __attribute__((swift_name("getVerbs(verbs:max:)")));
- (KMMYCSkikoPath *)incReserveExtraPtCount:(int32_t)extraPtCount __attribute__((swift_name("incReserve(extraPtCount:)")));
- (BOOL)isInterpolatableCompare:(KMMYCSkikoPath * _Nullable)compare __attribute__((swift_name("isInterpolatable(compare:)")));
- (KMMYCSkikoPathSegmentIterator *)iterator __attribute__((swift_name("iterator()")));
- (KMMYCSkikoPathSegmentIterator *)iteratorForceClose:(BOOL)forceClose __attribute__((swift_name("iterator(forceClose:)")));
- (KMMYCSkikoPath *)lineToX:(float)x y:(float)y __attribute__((swift_name("lineTo(x:y:)")));
- (KMMYCSkikoPath *)lineToP:(KMMYCSkikoPoint *)p __attribute__((swift_name("lineTo(p:)")));
- (KMMYCSkikoPath *)makeLerpEnding:(KMMYCSkikoPath * _Nullable)ending weight:(float)weight __attribute__((swift_name("makeLerp(ending:weight:)")));
- (KMMYCSkikoPath *)moveToX:(float)x y:(float)y __attribute__((swift_name("moveTo(x:y:)")));
- (KMMYCSkikoPath *)moveToP:(KMMYCSkikoPoint *)p __attribute__((swift_name("moveTo(p:)")));
- (KMMYCSkikoPath *)offsetDx:(float)dx dy:(float)dy dst:(KMMYCSkikoPath * _Nullable)dst __attribute__((swift_name("offset(dx:dy:dst:)")));
- (KMMYCSkikoPath *)quadToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 __attribute__((swift_name("quadTo(x1:y1:x2:y2:)")));
- (KMMYCSkikoPath *)quadToP1:(KMMYCSkikoPoint *)p1 p2:(KMMYCSkikoPoint *)p2 __attribute__((swift_name("quadTo(p1:p2:)")));
- (KMMYCSkikoPath *)rConicToDx1:(float)dx1 dy1:(float)dy1 dx2:(float)dx2 dy2:(float)dy2 w:(float)w __attribute__((swift_name("rConicTo(dx1:dy1:dx2:dy2:w:)")));
- (KMMYCSkikoPath *)rCubicToDx1:(float)dx1 dy1:(float)dy1 dx2:(float)dx2 dy2:(float)dy2 dx3:(float)dx3 dy3:(float)dy3 __attribute__((swift_name("rCubicTo(dx1:dy1:dx2:dy2:dx3:dy3:)")));
- (KMMYCSkikoPath *)rEllipticalArcToRx:(float)rx ry:(float)ry xAxisRotate:(float)xAxisRotate arc:(KMMYCSkikoPathEllipseArc *)arc direction:(KMMYCSkikoPathDirection *)direction dx:(float)dx dy:(float)dy __attribute__((swift_name("rEllipticalArcTo(rx:ry:xAxisRotate:arc:direction:dx:dy:)")));
- (KMMYCSkikoPath *)rLineToDx:(float)dx dy:(float)dy __attribute__((swift_name("rLineTo(dx:dy:)")));
- (KMMYCSkikoPath *)rMoveToDx:(float)dx dy:(float)dy __attribute__((swift_name("rMoveTo(dx:dy:)")));
- (KMMYCSkikoPath *)rQuadToDx1:(float)dx1 dy1:(float)dy1 dx2:(float)dx2 dy2:(float)dy2 __attribute__((swift_name("rQuadTo(dx1:dy1:dx2:dy2:)")));
- (KMMYCSkikoPath *)reset __attribute__((swift_name("reset()")));
- (KMMYCSkikoPath *)reverseAddPathSrc:(KMMYCSkikoPath * _Nullable)src __attribute__((swift_name("reverseAddPath(src:)")));
- (KMMYCSkikoPath *)rewind __attribute__((swift_name("rewind()")));
- (KMMYCKotlinByteArray *)serializeToBytes __attribute__((swift_name("serializeToBytes()")));
- (KMMYCSkikoPath *)setLastPtX:(float)x y:(float)y __attribute__((swift_name("setLastPt(x:y:)")));
- (KMMYCSkikoPath *)setVolatileIsVolatile:(BOOL)isVolatile __attribute__((swift_name("setVolatile(isVolatile:)")));
- (KMMYCSkikoPath *)swapOther:(KMMYCSkikoPath * _Nullable)other __attribute__((swift_name("swap(other:)")));
- (KMMYCSkikoPath *)tangentArcToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 radius:(float)radius __attribute__((swift_name("tangentArcTo(x1:y1:x2:y2:radius:)")));
- (KMMYCSkikoPath *)tangentArcToP1:(KMMYCSkikoPoint *)p1 p2:(KMMYCSkikoPoint *)p2 radius:(float)radius __attribute__((swift_name("tangentArcTo(p1:p2:radius:)")));
- (KMMYCSkikoPath *)transformMatrix:(KMMYCSkikoMatrix33 *)matrix applyPerspectiveClip:(BOOL)applyPerspectiveClip __attribute__((swift_name("transform(matrix:applyPerspectiveClip:)")));
- (KMMYCSkikoPath *)transformMatrix:(KMMYCSkikoMatrix33 *)matrix dst:(KMMYCSkikoPath * _Nullable)dst applyPerspectiveClip:(BOOL)applyPerspectiveClip __attribute__((swift_name("transform(matrix:dst:applyPerspectiveClip:)")));
- (KMMYCSkikoPath *)updateBoundsCache __attribute__((swift_name("updateBoundsCache()")));
@property (readonly) void * _Nullable approximateBytesUsed __attribute__((swift_name("approximateBytesUsed")));
@property (readonly) KMMYCKotlinArray<KMMYCSkikoPoint *> * _Nullable asLine __attribute__((swift_name("asLine")));
@property (readonly) KMMYCSkikoRect *bounds __attribute__((swift_name("bounds")));
@property KMMYCSkikoPathFillMode *fillMode __attribute__((swift_name("fillMode")));
@property (readonly) int32_t generationId __attribute__((swift_name("generationId")));
@property (readonly) BOOL isConvex __attribute__((swift_name("isConvex")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) BOOL isFinite __attribute__((swift_name("isFinite")));
@property (readonly) BOOL isLastContourClosed __attribute__((swift_name("isLastContourClosed")));
@property (readonly) KMMYCSkikoRect * _Nullable isOval __attribute__((swift_name("isOval")));
@property (readonly) KMMYCSkikoRRect * _Nullable isRRect __attribute__((swift_name("isRRect")));
@property (readonly) KMMYCSkikoRect * _Nullable isRect __attribute__((swift_name("isRect")));
@property (readonly) BOOL isValid __attribute__((swift_name("isValid")));
@property BOOL isVolatile __attribute__((swift_name("isVolatile")));
@property KMMYCSkikoPoint *lastPt __attribute__((swift_name("lastPt")));
@property (readonly) KMMYCKotlinArray<KMMYCSkikoPoint *> *points __attribute__((swift_name("points")));
@property (readonly) int32_t pointsCount __attribute__((swift_name("pointsCount")));
@property (readonly) int32_t segmentMasks __attribute__((swift_name("segmentMasks")));
@property (readonly) KMMYCKotlinArray<KMMYCSkikoPathVerb *> *verbs __attribute__((swift_name("verbs")));
@property (readonly) int32_t verbsCount __attribute__((swift_name("verbsCount")));
@end

__attribute__((swift_name("SkikoRect")))
@interface KMMYCSkikoRect : KMMYCBase
- (instancetype)initWithLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("init(left:top:right:bottom:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoRectCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCSkikoRect *)inflateSpread:(float)spread __attribute__((swift_name("inflate(spread:)")));
- (KMMYCSkikoRect * _Nullable)intersectOther:(KMMYCSkikoRect *)other __attribute__((swift_name("intersect(other:)")));
- (KMMYCSkikoRect *)offsetDx:(float)dx dy:(float)dy __attribute__((swift_name("offset(dx:dy:)")));
- (KMMYCSkikoRect *)offsetVec:(KMMYCSkikoPoint *)vec __attribute__((swift_name("offset(vec:)")));
- (KMMYCSkikoRect *)scaleScale:(float)scale __attribute__((swift_name("scale(scale:)")));
- (KMMYCSkikoRect *)scaleSx:(float)sx sy_:(float)sy __attribute__((swift_name("scale(sx:sy_:)")));
- (KMMYCSkikoIRect *)toIRect __attribute__((swift_name("toIRect()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float bottom __attribute__((swift_name("bottom")));
@property (readonly) float height_ __attribute__((swift_name("height_")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) float left __attribute__((swift_name("left")));
@property (readonly) float right __attribute__((swift_name("right")));
@property (readonly) float top __attribute__((swift_name("top")));
@property (readonly) float width_ __attribute__((swift_name("width_")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColor4f")))
@interface KMMYCSkikoColor4f : KMMYCBase
- (instancetype)initWithRgba:(KMMYCKotlinFloatArray *)rgba __attribute__((swift_name("init(rgba:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithC:(int32_t)c __attribute__((swift_name("init(c:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithR:(float)r g:(float)g b:(float)b a:(float)a __attribute__((swift_name("init(r:g:b:a:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoColor4fCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (KMMYCKotlinFloatArray *)flatten __attribute__((swift_name("flatten()")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCSkikoColor4f *)makeLerpOther:(KMMYCSkikoColor4f *)other weight:(float)weight __attribute__((swift_name("makeLerp(other:weight:)")));
- (int32_t)toColor __attribute__((swift_name("toColor()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (KMMYCSkikoColor4f *)withA_a:(float)_a __attribute__((swift_name("withA(_a:)")));
- (KMMYCSkikoColor4f *)withB_b:(float)_b __attribute__((swift_name("withB(_b:)")));
- (KMMYCSkikoColor4f *)withG_g:(float)_g __attribute__((swift_name("withG(_g:)")));
- (KMMYCSkikoColor4f *)withR_r:(float)_r __attribute__((swift_name("withR(_r:)")));
@property (readonly) float a __attribute__((swift_name("a")));
@property (readonly) float b __attribute__((swift_name("b")));
@property (readonly) float g __attribute__((swift_name("g")));
@property (readonly) float r __attribute__((swift_name("r")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorSpace")))
@interface KMMYCSkikoColorSpace : KMMYCSkikoManaged
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoColorSpaceCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoColor4f *)convertToColor:(KMMYCSkikoColorSpace * _Nullable)toColor color:(KMMYCSkikoColor4f *)color __attribute__((swift_name("convert(toColor:color:)")));
@property (readonly) BOOL isGammaCloseToSRGB __attribute__((swift_name("isGammaCloseToSRGB")));
@property (readonly) BOOL isGammaLinear __attribute__((swift_name("isGammaLinear")));
@property (readonly) BOOL isSRGB __attribute__((swift_name("isSRGB")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoBlendMode")))
@interface KMMYCSkikoBlendMode : KMMYCKotlinEnum<KMMYCSkikoBlendMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoBlendMode *clear __attribute__((swift_name("clear")));
@property (class, readonly) KMMYCSkikoBlendMode *src __attribute__((swift_name("src")));
@property (class, readonly) KMMYCSkikoBlendMode *dst __attribute__((swift_name("dst")));
@property (class, readonly) KMMYCSkikoBlendMode *srcOver __attribute__((swift_name("srcOver")));
@property (class, readonly) KMMYCSkikoBlendMode *dstOver __attribute__((swift_name("dstOver")));
@property (class, readonly) KMMYCSkikoBlendMode *srcIn __attribute__((swift_name("srcIn")));
@property (class, readonly) KMMYCSkikoBlendMode *dstIn __attribute__((swift_name("dstIn")));
@property (class, readonly) KMMYCSkikoBlendMode *srcOut __attribute__((swift_name("srcOut")));
@property (class, readonly) KMMYCSkikoBlendMode *dstOut __attribute__((swift_name("dstOut")));
@property (class, readonly) KMMYCSkikoBlendMode *srcAtop __attribute__((swift_name("srcAtop")));
@property (class, readonly) KMMYCSkikoBlendMode *dstAtop __attribute__((swift_name("dstAtop")));
@property (class, readonly) KMMYCSkikoBlendMode *xor_ __attribute__((swift_name("xor_")));
@property (class, readonly) KMMYCSkikoBlendMode *plus __attribute__((swift_name("plus")));
@property (class, readonly) KMMYCSkikoBlendMode *modulate __attribute__((swift_name("modulate")));
@property (class, readonly) KMMYCSkikoBlendMode *screen __attribute__((swift_name("screen")));
@property (class, readonly) KMMYCSkikoBlendMode *overlay __attribute__((swift_name("overlay")));
@property (class, readonly) KMMYCSkikoBlendMode *darken __attribute__((swift_name("darken")));
@property (class, readonly) KMMYCSkikoBlendMode *lighten __attribute__((swift_name("lighten")));
@property (class, readonly) KMMYCSkikoBlendMode *colorDodge __attribute__((swift_name("colorDodge")));
@property (class, readonly) KMMYCSkikoBlendMode *colorBurn __attribute__((swift_name("colorBurn")));
@property (class, readonly) KMMYCSkikoBlendMode *hardLight __attribute__((swift_name("hardLight")));
@property (class, readonly) KMMYCSkikoBlendMode *softLight __attribute__((swift_name("softLight")));
@property (class, readonly) KMMYCSkikoBlendMode *difference __attribute__((swift_name("difference")));
@property (class, readonly) KMMYCSkikoBlendMode *exclusion __attribute__((swift_name("exclusion")));
@property (class, readonly) KMMYCSkikoBlendMode *multiply __attribute__((swift_name("multiply")));
@property (class, readonly) KMMYCSkikoBlendMode *hue __attribute__((swift_name("hue")));
@property (class, readonly) KMMYCSkikoBlendMode *saturation __attribute__((swift_name("saturation")));
@property (class, readonly) KMMYCSkikoBlendMode *color __attribute__((swift_name("color")));
@property (class, readonly) KMMYCSkikoBlendMode *luminosity __attribute__((swift_name("luminosity")));
+ (KMMYCKotlinArray<KMMYCSkikoBlendMode *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorFilter")))
@interface KMMYCSkikoColorFilter : KMMYCSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoColorFilterCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImageFilter")))
@interface KMMYCSkikoImageFilter : KMMYCSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoImageFilterCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMaskFilter")))
@interface KMMYCSkikoMaskFilter : KMMYCSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoMaskFilterCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaintMode")))
@interface KMMYCSkikoPaintMode : KMMYCKotlinEnum<KMMYCSkikoPaintMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPaintMode *fill __attribute__((swift_name("fill")));
@property (class, readonly) KMMYCSkikoPaintMode *stroke __attribute__((swift_name("stroke")));
@property (class, readonly) KMMYCSkikoPaintMode *strokeAndFill __attribute__((swift_name("strokeAndFill")));
+ (KMMYCKotlinArray<KMMYCSkikoPaintMode *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathEffect")))
@interface KMMYCSkikoPathEffect : KMMYCSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoPathEffectCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoPathEffect *)makeComposeInner:(KMMYCSkikoPathEffect * _Nullable)inner __attribute__((swift_name("makeCompose(inner:)")));
- (KMMYCSkikoPathEffect *)makeSumSecond:(KMMYCSkikoPathEffect * _Nullable)second __attribute__((swift_name("makeSum(second:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaintStrokeCap")))
@interface KMMYCSkikoPaintStrokeCap : KMMYCKotlinEnum<KMMYCSkikoPaintStrokeCap *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPaintStrokeCap *butt __attribute__((swift_name("butt")));
@property (class, readonly) KMMYCSkikoPaintStrokeCap *round __attribute__((swift_name("round")));
@property (class, readonly) KMMYCSkikoPaintStrokeCap *square __attribute__((swift_name("square")));
+ (KMMYCKotlinArray<KMMYCSkikoPaintStrokeCap *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaintStrokeJoin")))
@interface KMMYCSkikoPaintStrokeJoin : KMMYCKotlinEnum<KMMYCSkikoPaintStrokeJoin *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPaintStrokeJoin *miter __attribute__((swift_name("miter")));
@property (class, readonly) KMMYCSkikoPaintStrokeJoin *round __attribute__((swift_name("round")));
@property (class, readonly) KMMYCSkikoPaintStrokeJoin *bevel __attribute__((swift_name("bevel")));
+ (KMMYCKotlinArray<KMMYCSkikoPaintStrokeJoin *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoShader.Companion")))
@interface KMMYCSkikoShaderCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoShaderCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoShader *)makeBlendMode:(KMMYCSkikoBlendMode *)mode dst:(KMMYCSkikoShader * _Nullable)dst src:(KMMYCSkikoShader * _Nullable)src __attribute__((swift_name("makeBlend(mode:dst:src:)")));
- (KMMYCSkikoShader *)makeColorColor:(int32_t)color __attribute__((swift_name("makeColor(color:)")));
- (KMMYCSkikoShader *)makeColorColor:(KMMYCSkikoColor4f *)color space:(KMMYCSkikoColorSpace * _Nullable)space __attribute__((swift_name("makeColor(color:space:)")));
- (KMMYCSkikoShader *)makeEmpty __attribute__((swift_name("makeEmpty()")));
- (KMMYCSkikoShader *)makeFractalNoiseBaseFrequencyX:(float)baseFrequencyX baseFrequencyY:(float)baseFrequencyY numOctaves:(int32_t)numOctaves seed:(float)seed tileSize:(KMMYCSkikoISize *)tileSize __attribute__((swift_name("makeFractalNoise(baseFrequencyX:baseFrequencyY:numOctaves:seed:tileSize:)")));
- (KMMYCSkikoShader *)makeLinearGradientX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 colors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors cs:(KMMYCSkikoColorSpace * _Nullable)cs positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeLinearGradient(x0:y0:x1:y1:colors:cs:positions:style:)")));
- (KMMYCSkikoShader *)makeLinearGradientX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeLinearGradient(x0:y0:x1:y1:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeLinearGradientP0:(KMMYCSkikoPoint *)p0 p1:(KMMYCSkikoPoint *)p1 colors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors cs:(KMMYCSkikoColorSpace * _Nullable)cs positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeLinearGradient(p0:p1:colors:cs:positions:style:)")));
- (KMMYCSkikoShader *)makeLinearGradientP0:(KMMYCSkikoPoint *)p0 p1:(KMMYCSkikoPoint *)p1 colors:(KMMYCKotlinIntArray *)colors __attribute__((swift_name("makeLinearGradient(p0:p1:colors:)")));
- (KMMYCSkikoShader *)makeLinearGradientP0:(KMMYCSkikoPoint *)p0 p1:(KMMYCSkikoPoint *)p1 colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeLinearGradient(p0:p1:colors:positions:)")));
- (KMMYCSkikoShader *)makeLinearGradientP0:(KMMYCSkikoPoint *)p0 p1:(KMMYCSkikoPoint *)p1 colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeLinearGradient(p0:p1:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeRadialGradientX:(float)x y:(float)y r:(float)r colors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors cs:(KMMYCSkikoColorSpace * _Nullable)cs positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeRadialGradient(x:y:r:colors:cs:positions:style:)")));
- (KMMYCSkikoShader *)makeRadialGradientX:(float)x y:(float)y r:(float)r colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeRadialGradient(x:y:r:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeRadialGradientCenter:(KMMYCSkikoPoint *)center r:(float)r colors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors cs:(KMMYCSkikoColorSpace * _Nullable)cs positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeRadialGradient(center:r:colors:cs:positions:style:)")));
- (KMMYCSkikoShader *)makeRadialGradientCenter:(KMMYCSkikoPoint *)center r:(float)r colors:(KMMYCKotlinIntArray *)colors __attribute__((swift_name("makeRadialGradient(center:r:colors:)")));
- (KMMYCSkikoShader *)makeRadialGradientCenter:(KMMYCSkikoPoint *)center r:(float)r colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeRadialGradient(center:r:colors:positions:)")));
- (KMMYCSkikoShader *)makeRadialGradientCenter:(KMMYCSkikoPoint *)center r:(float)r colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeRadialGradient(center:r:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeSweepGradientX:(float)x y:(float)y startAngle:(float)startAngle endAngle:(float)endAngle colors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors cs:(KMMYCSkikoColorSpace * _Nullable)cs positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(x:y:startAngle:endAngle:colors:cs:positions:style:)")));
- (KMMYCSkikoShader *)makeSweepGradientX:(float)x y:(float)y startAngle:(float)startAngle endAngle:(float)endAngle colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(x:y:startAngle:endAngle:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeSweepGradientX:(float)x y:(float)y colors:(KMMYCKotlinIntArray *)colors __attribute__((swift_name("makeSweepGradient(x:y:colors:)")));
- (KMMYCSkikoShader *)makeSweepGradientX:(float)x y:(float)y colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeSweepGradient(x:y:colors:positions:)")));
- (KMMYCSkikoShader *)makeSweepGradientX:(float)x y:(float)y colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(x:y:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeSweepGradientCenter:(KMMYCSkikoPoint *)center startAngle:(float)startAngle endAngle:(float)endAngle colors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors cs:(KMMYCSkikoColorSpace * _Nullable)cs positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(center:startAngle:endAngle:colors:cs:positions:style:)")));
- (KMMYCSkikoShader *)makeSweepGradientCenter:(KMMYCSkikoPoint *)center startAngle:(float)startAngle endAngle:(float)endAngle colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(center:startAngle:endAngle:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeSweepGradientCenter:(KMMYCSkikoPoint *)center colors:(KMMYCKotlinIntArray *)colors __attribute__((swift_name("makeSweepGradient(center:colors:)")));
- (KMMYCSkikoShader *)makeSweepGradientCenter:(KMMYCSkikoPoint *)center colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeSweepGradient(center:colors:positions:)")));
- (KMMYCSkikoShader *)makeSweepGradientCenter:(KMMYCSkikoPoint *)center colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(center:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeTurbulenceBaseFrequencyX:(float)baseFrequencyX baseFrequencyY:(float)baseFrequencyY numOctaves:(int32_t)numOctaves seed:(float)seed tileSize:(KMMYCSkikoISize *)tileSize __attribute__((swift_name("makeTurbulence(baseFrequencyX:baseFrequencyY:numOctaves:seed:tileSize:)")));
- (KMMYCSkikoShader *)makeTwoPointConicalGradientX0:(float)x0 y0:(float)y0 r0:(float)r0 x1:(float)x1 y1:(float)y1 r1:(float)r1 colors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors cs:(KMMYCSkikoColorSpace * _Nullable)cs positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeTwoPointConicalGradient(x0:y0:r0:x1:y1:r1:colors:cs:positions:style:)")));
- (KMMYCSkikoShader *)makeTwoPointConicalGradientX0:(float)x0 y0:(float)y0 r0:(float)r0 x1:(float)x1 y1:(float)y1 r1:(float)r1 colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeTwoPointConicalGradient(x0:y0:r0:x1:y1:r1:colors:positions:style:)")));
- (KMMYCSkikoShader *)makeTwoPointConicalGradientP0:(KMMYCSkikoPoint *)p0 r0:(float)r0 p1:(KMMYCSkikoPoint *)p1 r1:(float)r1 colors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors cs:(KMMYCSkikoColorSpace * _Nullable)cs positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeTwoPointConicalGradient(p0:r0:p1:r1:colors:cs:positions:style:)")));
- (KMMYCSkikoShader *)makeTwoPointConicalGradientP0:(KMMYCSkikoPoint *)p0 r0:(float)r0 p1:(KMMYCSkikoPoint *)p1 r1:(float)r1 colors:(KMMYCKotlinIntArray *)colors __attribute__((swift_name("makeTwoPointConicalGradient(p0:r0:p1:r1:colors:)")));
- (KMMYCSkikoShader *)makeTwoPointConicalGradientP0:(KMMYCSkikoPoint *)p0 r0:(float)r0 p1:(KMMYCSkikoPoint *)p1 r1:(float)r1 colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeTwoPointConicalGradient(p0:r0:p1:r1:colors:positions:)")));
- (KMMYCSkikoShader *)makeTwoPointConicalGradientP0:(KMMYCSkikoPoint *)p0 r0:(float)r0 p1:(KMMYCSkikoPoint *)p1 r1:(float)r1 colors:(KMMYCKotlinIntArray *)colors positions:(KMMYCKotlinFloatArray * _Nullable)positions style:(KMMYCSkikoGradientStyle *)style __attribute__((swift_name("makeTwoPointConicalGradient(p0:r0:p1:r1:colors:positions:style:)")));
@end

__attribute__((swift_name("Ui_textSystemFontFamily")))
@interface KMMYCUi_textSystemFontFamily : KMMYCUi_textFontFamily
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textGenericFontFamily")))
@interface KMMYCUi_textGenericFontFamily : KMMYCUi_textSystemFontFamily
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLocale.Companion")))
@interface KMMYCUi_textLocaleCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textLocaleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCUi_textLocale *current __attribute__((swift_name("current")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformSpanStyle.Companion")))
@interface KMMYCUi_textPlatformSpanStyleCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textPlatformSpanStyleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCUi_textPlatformSpanStyle *Default __attribute__((swift_name("Default")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformParagraphStyle.Companion")))
@interface KMMYCUi_textPlatformParagraphStyleCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCUi_textPlatformParagraphStyleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCUi_textPlatformParagraphStyle *Default __attribute__((swift_name("Default")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsVertices")))
@interface KMMYCUi_graphicsVertices : KMMYCBase
- (instancetype)initWithVertexMode:(int32_t)vertexMode positions:(NSArray<id> *)positions textureCoordinates:(NSArray<id> *)textureCoordinates colors:(NSArray<id> *)colors indices:(NSArray<KMMYCInt *> *)indices __attribute__((swift_name("init(vertexMode:positions:textureCoordinates:colors:indices:)"))) __attribute__((objc_designated_initializer));
@property (readonly) KMMYCKotlinIntArray *colors __attribute__((swift_name("colors")));
@property (readonly) KMMYCKotlinShortArray *indices __attribute__((swift_name("indices")));
@property (readonly) KMMYCKotlinFloatArray *positions __attribute__((swift_name("positions")));
@property (readonly) KMMYCKotlinFloatArray *textureCoordinates __attribute__((swift_name("textureCoordinates")));
@property (readonly) int32_t vertexMode __attribute__((swift_name("vertexMode")));
@end

__attribute__((swift_name("Ui_textFontResourceLoader")))
@protocol KMMYCUi_textFontResourceLoader
@required
- (id)loadFont:(id<KMMYCUi_textFont>)font __attribute__((swift_name("load(font:)"))) __attribute__((deprecated("Replaced by FontFamily.Resolver, this method should not be called")));
@end

__attribute__((swift_name("Ui_textParagraphIntrinsics")))
@protocol KMMYCUi_textParagraphIntrinsics
@required
@property (readonly) BOOL hasStaleResolvedFonts __attribute__((swift_name("hasStaleResolvedFonts")));
@property (readonly) float maxIntrinsicWidth __attribute__((swift_name("maxIntrinsicWidth")));
@property (readonly) float minIntrinsicWidth __attribute__((swift_name("minIntrinsicWidth")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textMultiParagraphIntrinsics")))
@interface KMMYCUi_textMultiParagraphIntrinsics : KMMYCBase <KMMYCUi_textParagraphIntrinsics>
- (instancetype)initWithAnnotatedString:(KMMYCUi_textAnnotatedString *)annotatedString style:(KMMYCUi_textTextStyle *)style placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders density:(id<KMMYCUi_unitDensity>)density resourceLoader:(id<KMMYCUi_textFontResourceLoader>)resourceLoader __attribute__((swift_name("init(annotatedString:style:placeholders:density:resourceLoader:)"))) __attribute__((objc_designated_initializer)) __attribute__((deprecated("Font.ResourceLoader is deprecated, call with fontFamilyResolver")));
- (instancetype)initWithAnnotatedString:(KMMYCUi_textAnnotatedString *)annotatedString style:(KMMYCUi_textTextStyle *)style placeholders:(NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *)placeholders density:(id<KMMYCUi_unitDensity>)density fontFamilyResolver:(id<KMMYCUi_textFontFamilyResolver>)fontFamilyResolver __attribute__((swift_name("init(annotatedString:style:placeholders:density:fontFamilyResolver:)"))) __attribute__((objc_designated_initializer));
@property (readonly) KMMYCUi_textAnnotatedString *annotatedString __attribute__((swift_name("annotatedString")));
@property (readonly) BOOL hasStaleResolvedFonts __attribute__((swift_name("hasStaleResolvedFonts")));
@property (readonly) float maxIntrinsicWidth __attribute__((swift_name("maxIntrinsicWidth")));
@property (readonly) float minIntrinsicWidth __attribute__((swift_name("minIntrinsicWidth")));
@property (readonly) NSArray<KMMYCUi_textAnnotatedStringRange<KMMYCUi_textPlaceholder *> *> *placeholders __attribute__((swift_name("placeholders")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPath.Companion")))
@interface KMMYCSkikoPathCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoPathCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCKotlinArray<KMMYCSkikoPoint *> *)convertConicToQuadsP0:(KMMYCSkikoPoint *)p0 p1:(KMMYCSkikoPoint *)p1 p2:(KMMYCSkikoPoint *)p2 w:(float)w pow2:(int32_t)pow2 __attribute__((swift_name("convertConicToQuads(p0:p1:p2:w:pow2:)")));
- (BOOL)isCubicDegenerateP1:(KMMYCSkikoPoint *)p1 p2:(KMMYCSkikoPoint *)p2 p3:(KMMYCSkikoPoint *)p3 p4:(KMMYCSkikoPoint *)p4 exact:(BOOL)exact __attribute__((swift_name("isCubicDegenerate(p1:p2:p3:p4:exact:)")));
- (BOOL)isLineDegenerateP1:(KMMYCSkikoPoint *)p1 p2:(KMMYCSkikoPoint *)p2 exact:(BOOL)exact __attribute__((swift_name("isLineDegenerate(p1:p2:exact:)")));
- (BOOL)isQuadDegenerateP1:(KMMYCSkikoPoint *)p1 p2:(KMMYCSkikoPoint *)p2 p3:(KMMYCSkikoPoint *)p3 exact:(BOOL)exact __attribute__((swift_name("isQuadDegenerate(p1:p2:p3:exact:)")));
- (KMMYCSkikoPath * _Nullable)makeCombiningOne:(KMMYCSkikoPath *)one two:(KMMYCSkikoPath *)two op:(KMMYCSkikoPathOp *)op __attribute__((swift_name("makeCombining(one:two:op:)")));
- (KMMYCSkikoPath *)makeFromBytesData:(KMMYCKotlinByteArray *)data __attribute__((swift_name("makeFromBytes(data:)")));
- (KMMYCSkikoPath *)makeFromSVGStringSvg:(NSString *)svg __attribute__((swift_name("makeFromSVGString(svg:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathDirection")))
@interface KMMYCSkikoPathDirection : KMMYCKotlinEnum<KMMYCSkikoPathDirection *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPathDirection *clockwise __attribute__((swift_name("clockwise")));
@property (class, readonly) KMMYCSkikoPathDirection *counterClockwise __attribute__((swift_name("counterClockwise")));
+ (KMMYCKotlinArray<KMMYCSkikoPathDirection *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix33")))
@interface KMMYCSkikoMatrix33 : KMMYCBase
- (instancetype)initWithMat:(KMMYCKotlinFloatArray *)mat __attribute__((swift_name("init(mat:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoMatrix33Companion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoMatrix44 *)asMatrix44 __attribute__((swift_name("asMatrix44()")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCSkikoMatrix33 *)makeConcatOther:(KMMYCSkikoMatrix33 *)other __attribute__((swift_name("makeConcat(other:)")));
- (KMMYCSkikoMatrix33 *)makePreScaleSx:(float)sx sy:(float)sy __attribute__((swift_name("makePreScale(sx:sy:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCKotlinFloatArray *mat __attribute__((swift_name("mat")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPoint")))
@interface KMMYCSkikoPoint : KMMYCBase
- (instancetype)initWithX:(float)x y:(float)y __attribute__((swift_name("init(x:y:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoPointCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCSkikoPoint *)offsetDx:(float)dx dy:(float)dy __attribute__((swift_name("offset(dx:dy:)")));
- (KMMYCSkikoPoint *)offsetVec:(KMMYCSkikoPoint *)vec __attribute__((swift_name("offset(vec:)")));
- (KMMYCSkikoPoint *)scaleScale:(float)scale __attribute__((swift_name("scale(scale:)")));
- (KMMYCSkikoPoint *)scaleSx:(float)sx sy:(float)sy __attribute__((swift_name("scale(sx:sy:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) float x __attribute__((swift_name("x")));
@property (readonly) float y __attribute__((swift_name("y")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRRect")))
@interface KMMYCSkikoRRect : KMMYCSkikoRect
- (instancetype)initWithLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("init(left:top:right:bottom:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoRRectCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCSkikoRect *)inflateSpread:(float)spread __attribute__((swift_name("inflate(spread:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCKotlinFloatArray *radii __attribute__((swift_name("radii")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathEllipseArc")))
@interface KMMYCSkikoPathEllipseArc : KMMYCKotlinEnum<KMMYCSkikoPathEllipseArc *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPathEllipseArc *smaller __attribute__((swift_name("smaller")));
@property (class, readonly) KMMYCSkikoPathEllipseArc *larger __attribute__((swift_name("larger")));
+ (KMMYCKotlinArray<KMMYCSkikoPathEllipseArc *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathVerb")))
@interface KMMYCSkikoPathVerb : KMMYCKotlinEnum<KMMYCSkikoPathVerb *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPathVerb *move __attribute__((swift_name("move")));
@property (class, readonly) KMMYCSkikoPathVerb *line __attribute__((swift_name("line")));
@property (class, readonly) KMMYCSkikoPathVerb *quad __attribute__((swift_name("quad")));
@property (class, readonly) KMMYCSkikoPathVerb *conic __attribute__((swift_name("conic")));
@property (class, readonly) KMMYCSkikoPathVerb *cubic __attribute__((swift_name("cubic")));
@property (class, readonly) KMMYCSkikoPathVerb *close __attribute__((swift_name("close")));
@property (class, readonly) KMMYCSkikoPathVerb *done __attribute__((swift_name("done")));
+ (KMMYCKotlinArray<KMMYCSkikoPathVerb *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((swift_name("KotlinMutableIterator")))
@protocol KMMYCKotlinMutableIterator <KMMYCKotlinIterator>
@required
- (void)remove __attribute__((swift_name("remove()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathSegmentIterator")))
@interface KMMYCSkikoPathSegmentIterator : KMMYCSkikoManaged <KMMYCKotlinMutableIterator>
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoPathSegmentIteratorCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (KMMYCSkikoPathSegment * _Nullable)next __attribute__((swift_name("next()")));
- (void)remove __attribute__((swift_name("remove()")));
@property KMMYCSkikoPathSegment * _Nullable _nextSegment __attribute__((swift_name("_nextSegment")));
@property (readonly) KMMYCSkikoPath * _Nullable _path __attribute__((swift_name("_path")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinByteArray")))
@interface KMMYCKotlinByteArray : KMMYCBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(KMMYCByte *(^)(KMMYCInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int8_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (KMMYCKotlinByteIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int8_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathFillMode")))
@interface KMMYCSkikoPathFillMode : KMMYCKotlinEnum<KMMYCSkikoPathFillMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPathFillMode *winding __attribute__((swift_name("winding")));
@property (class, readonly) KMMYCSkikoPathFillMode *evenOdd __attribute__((swift_name("evenOdd")));
@property (class, readonly) KMMYCSkikoPathFillMode *inverseWinding __attribute__((swift_name("inverseWinding")));
@property (class, readonly) KMMYCSkikoPathFillMode *inverseEvenOdd __attribute__((swift_name("inverseEvenOdd")));
+ (KMMYCKotlinArray<KMMYCSkikoPathFillMode *> *)values __attribute__((swift_name("values()")));
- (KMMYCSkikoPathFillMode *)inverse __attribute__((swift_name("inverse()")));
@property (readonly) BOOL isInverse __attribute__((swift_name("isInverse")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRect.Companion")))
@interface KMMYCSkikoRectCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoRectCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRect *)makeLTRBL:(float)l t:(float)t r:(float)r b:(float)b __attribute__((swift_name("makeLTRB(l:t:r:b:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRect *)makeWHW:(float)w h:(float)h __attribute__((swift_name("makeWH(w:h:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRect *)makeWHSize:(KMMYCSkikoPoint *)size __attribute__((swift_name("makeWH(size:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRect *)makeXYWHL:(float)l t:(float)t w:(float)w h:(float)h __attribute__((swift_name("makeXYWH(l:t:w:h:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoIRect")))
@interface KMMYCSkikoIRect : KMMYCBase
@property (class, readonly, getter=companion) KMMYCSkikoIRectCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCSkikoIRect * _Nullable)intersectOther:(KMMYCSkikoIRect *)other __attribute__((swift_name("intersect(other:)")));
- (KMMYCSkikoIRect *)offsetDx:(int32_t)dx dy:(int32_t)dy __attribute__((swift_name("offset(dx:dy:)")));
- (KMMYCSkikoIRect *)offsetVec:(KMMYCSkikoIPoint *)vec __attribute__((swift_name("offset(vec:)")));
- (KMMYCSkikoRect *)toRect __attribute__((swift_name("toRect()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t bottom __attribute__((swift_name("bottom")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) int32_t left __attribute__((swift_name("left")));
@property (readonly) int32_t right __attribute__((swift_name("right")));
@property (readonly) int32_t top __attribute__((swift_name("top")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColor4f.Companion")))
@interface KMMYCSkikoColor4fCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoColor4fCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCKotlinFloatArray *)flattenArrayColors:(KMMYCKotlinArray<KMMYCSkikoColor4f *> *)colors __attribute__((swift_name("flattenArray(colors:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorSpace.Companion")))
@interface KMMYCSkikoColorSpaceCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoColorSpaceCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCSkikoColorSpace *displayP3 __attribute__((swift_name("displayP3")));
@property (readonly) KMMYCSkikoColorSpace *sRGB __attribute__((swift_name("sRGB")));
@property (readonly) KMMYCSkikoColorSpace *sRGBLinear __attribute__((swift_name("sRGBLinear")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorFilter.Companion")))
@interface KMMYCSkikoColorFilterCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoColorFilterCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoColorFilter *)makeBlendColor:(int32_t)color mode:(KMMYCSkikoBlendMode *)mode __attribute__((swift_name("makeBlend(color:mode:)")));
- (KMMYCSkikoColorFilter *)makeComposedOuter:(KMMYCSkikoColorFilter * _Nullable)outer inner:(KMMYCSkikoColorFilter * _Nullable)inner __attribute__((swift_name("makeComposed(outer:inner:)")));
- (KMMYCSkikoColorFilter *)makeHSLAMatrixMatrix:(KMMYCSkikoColorMatrix *)matrix __attribute__((swift_name("makeHSLAMatrix(matrix:)")));
- (KMMYCSkikoColorFilter *)makeHighContrastGrayscale:(BOOL)grayscale mode:(KMMYCSkikoInversionMode *)mode contrast:(float)contrast __attribute__((swift_name("makeHighContrast(grayscale:mode:contrast:)")));
- (KMMYCSkikoColorFilter *)makeLerpDst:(KMMYCSkikoColorFilter * _Nullable)dst src:(KMMYCSkikoColorFilter * _Nullable)src t:(float)t __attribute__((swift_name("makeLerp(dst:src:t:)")));
- (KMMYCSkikoColorFilter *)makeLightingColorMul:(int32_t)colorMul colorAdd:(int32_t)colorAdd __attribute__((swift_name("makeLighting(colorMul:colorAdd:)")));
- (KMMYCSkikoColorFilter *)makeMatrixMatrix:(KMMYCSkikoColorMatrix *)matrix __attribute__((swift_name("makeMatrix(matrix:)")));
- (KMMYCSkikoColorFilter *)makeOverdrawColors:(KMMYCKotlinIntArray *)colors __attribute__((swift_name("makeOverdraw(colors:)")));
- (KMMYCSkikoColorFilter *)makeTableTable:(KMMYCKotlinByteArray *)table __attribute__((swift_name("makeTable(table:)")));
- (KMMYCSkikoColorFilter *)makeTableARGBA:(KMMYCKotlinByteArray * _Nullable)a r:(KMMYCKotlinByteArray * _Nullable)r g:(KMMYCKotlinByteArray * _Nullable)g b:(KMMYCKotlinByteArray * _Nullable)b __attribute__((swift_name("makeTableARGB(a:r:g:b:)")));
@property (readonly) KMMYCSkikoColorFilter *luma __attribute__((swift_name("luma")));
@property (readonly) KMMYCSkikoColorFilter *sRGBToLinearGamma __attribute__((swift_name("sRGBToLinearGamma")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImageFilter.Companion")))
@interface KMMYCSkikoImageFilterCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoImageFilterCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoImageFilter *)makeAlphaThresholdR:(KMMYCSkikoRegion * _Nullable)r innerMin:(float)innerMin outerMax:(float)outerMax input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeAlphaThreshold(r:innerMin:outerMax:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeArithmeticK1:(float)k1 k2:(float)k2 k3:(float)k3 k4:(float)k4 enforcePMColor:(BOOL)enforcePMColor bg:(KMMYCSkikoImageFilter * _Nullable)bg fg:(KMMYCSkikoImageFilter * _Nullable)fg crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeArithmetic(k1:k2:k3:k4:enforcePMColor:bg:fg:crop:)")));
- (KMMYCSkikoImageFilter *)makeBlendBlendMode:(KMMYCSkikoBlendMode *)blendMode bg:(KMMYCSkikoImageFilter * _Nullable)bg fg:(KMMYCSkikoImageFilter * _Nullable)fg crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeBlend(blendMode:bg:fg:crop:)")));
- (KMMYCSkikoImageFilter *)makeBlurSigmaX:(float)sigmaX sigmaY:(float)sigmaY mode:(KMMYCSkikoFilterTileMode *)mode input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeBlur(sigmaX:sigmaY:mode:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeColorFilterF:(KMMYCSkikoColorFilter * _Nullable)f input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeColorFilter(f:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeComposeOuter:(KMMYCSkikoImageFilter * _Nullable)outer inner:(KMMYCSkikoImageFilter * _Nullable)inner __attribute__((swift_name("makeCompose(outer:inner:)")));
- (KMMYCSkikoImageFilter *)makeDilateRx:(float)rx ry:(float)ry input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDilate(rx:ry:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeDisplacementMapX:(KMMYCSkikoColorChannel *)x y:(KMMYCSkikoColorChannel *)y scale:(float)scale displacement:(KMMYCSkikoImageFilter * _Nullable)displacement color:(KMMYCSkikoImageFilter * _Nullable)color crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDisplacementMap(x:y:scale:displacement:color:crop:)")));
- (KMMYCSkikoImageFilter *)makeDistantLitDiffuseX:(float)x y:(float)y z:(float)z lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale kd:(float)kd input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDistantLitDiffuse(x:y:z:lightColor:surfaceScale:kd:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeDistantLitSpecularX:(float)x y:(float)y z:(float)z lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale ks:(float)ks shininess:(float)shininess input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDistantLitSpecular(x:y:z:lightColor:surfaceScale:ks:shininess:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeDropShadowDx:(float)dx dy:(float)dy sigmaX:(float)sigmaX sigmaY:(float)sigmaY color:(int32_t)color input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDropShadow(dx:dy:sigmaX:sigmaY:color:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeDropShadowOnlyDx:(float)dx dy:(float)dy sigmaX:(float)sigmaX sigmaY:(float)sigmaY color:(int32_t)color input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDropShadowOnly(dx:dy:sigmaX:sigmaY:color:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeErodeRx:(float)rx ry:(float)ry input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeErode(rx:ry:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeImageImage:(KMMYCSkikoImage *)image __attribute__((swift_name("makeImage(image:)")));
- (KMMYCSkikoImageFilter *)makeImageImage:(KMMYCSkikoImage * _Nullable)image src:(KMMYCSkikoRect *)src dst:(KMMYCSkikoRect *)dst mode:(id<KMMYCSkikoSamplingMode>)mode __attribute__((swift_name("makeImage(image:src:dst:mode:)")));
- (KMMYCSkikoImageFilter *)makeMagnifierR:(KMMYCSkikoRect *)r inset:(float)inset input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeMagnifier(r:inset:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeMatrixConvolutionKernelW:(int32_t)kernelW kernelH:(int32_t)kernelH kernel:(KMMYCKotlinFloatArray * _Nullable)kernel gain:(float)gain bias:(float)bias offsetX:(int32_t)offsetX offsetY:(int32_t)offsetY tileMode:(KMMYCSkikoFilterTileMode *)tileMode convolveAlpha:(BOOL)convolveAlpha input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeMatrixConvolution(kernelW:kernelH:kernel:gain:bias:offsetX:offsetY:tileMode:convolveAlpha:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeMatrixTransformMatrix:(KMMYCSkikoMatrix33 *)matrix mode:(id<KMMYCSkikoSamplingMode>)mode input:(KMMYCSkikoImageFilter * _Nullable)input __attribute__((swift_name("makeMatrixTransform(matrix:mode:input:)")));
- (KMMYCSkikoImageFilter *)makeMergeFilters:(KMMYCKotlinArray<KMMYCSkikoImageFilter *> *)filters crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeMerge(filters:crop:)")));
- (KMMYCSkikoImageFilter *)makeOffsetDx:(float)dx dy:(float)dy input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeOffset(dx:dy:input:crop:)")));
- (KMMYCSkikoImageFilter *)makePaintPaint:(KMMYCSkikoPaint * _Nullable)paint crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makePaint(paint:crop:)")));
- (KMMYCSkikoImageFilter *)makePointLitDiffuseX:(float)x y:(float)y z:(float)z lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale kd:(float)kd input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makePointLitDiffuse(x:y:z:lightColor:surfaceScale:kd:input:crop:)")));
- (KMMYCSkikoImageFilter *)makePointLitSpecularX:(float)x y:(float)y z:(float)z lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale ks:(float)ks shininess:(float)shininess input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makePointLitSpecular(x:y:z:lightColor:surfaceScale:ks:shininess:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeRuntimeShaderRuntimeShaderBuilder:(KMMYCSkikoRuntimeShaderBuilder *)runtimeShaderBuilder shaderNames:(KMMYCKotlinArray<NSString *> *)shaderNames inputs:(KMMYCKotlinArray<KMMYCSkikoImageFilter *> *)inputs __attribute__((swift_name("makeRuntimeShader(runtimeShaderBuilder:shaderNames:inputs:)")));
- (KMMYCSkikoImageFilter *)makeRuntimeShaderRuntimeShaderBuilder:(KMMYCSkikoRuntimeShaderBuilder *)runtimeShaderBuilder shaderName:(NSString *)shaderName input:(KMMYCSkikoImageFilter * _Nullable)input __attribute__((swift_name("makeRuntimeShader(runtimeShaderBuilder:shaderName:input:)")));
- (KMMYCSkikoImageFilter *)makeSpotLitDiffuseX0:(float)x0 y0:(float)y0 z0:(float)z0 x1:(float)x1 y1:(float)y1 z1:(float)z1 falloffExponent:(float)falloffExponent cutoffAngle:(float)cutoffAngle lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale kd:(float)kd input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeSpotLitDiffuse(x0:y0:z0:x1:y1:z1:falloffExponent:cutoffAngle:lightColor:surfaceScale:kd:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeSpotLitSpecularX0:(float)x0 y0:(float)y0 z0:(float)z0 x1:(float)x1 y1:(float)y1 z1:(float)z1 falloffExponent:(float)falloffExponent cutoffAngle:(float)cutoffAngle lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale ks:(float)ks shininess:(float)shininess input:(KMMYCSkikoImageFilter * _Nullable)input crop:(KMMYCSkikoIRect * _Nullable)crop __attribute__((swift_name("makeSpotLitSpecular(x0:y0:z0:x1:y1:z1:falloffExponent:cutoffAngle:lightColor:surfaceScale:ks:shininess:input:crop:)")));
- (KMMYCSkikoImageFilter *)makeTileSrc:(KMMYCSkikoRect *)src dst:(KMMYCSkikoRect *)dst input:(KMMYCSkikoImageFilter * _Nullable)input __attribute__((swift_name("makeTile(src:dst:input:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMaskFilter.Companion")))
@interface KMMYCSkikoMaskFilterCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoMaskFilterCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoMaskFilter *)makeBlurMode:(KMMYCSkikoFilterBlurMode *)mode sigma:(float)sigma respectCTM:(BOOL)respectCTM __attribute__((swift_name("makeBlur(mode:sigma:respectCTM:)")));
- (KMMYCSkikoMaskFilter *)makeClipMin:(int32_t)min max:(int32_t)max __attribute__((swift_name("makeClip(min:max:)")));
- (KMMYCSkikoMaskFilter *)makeGammaGamma:(float)gamma __attribute__((swift_name("makeGamma(gamma:)")));
- (KMMYCSkikoMaskFilter *)makeShaderS:(KMMYCSkikoShader * _Nullable)s __attribute__((swift_name("makeShader(s:)")));
- (KMMYCSkikoMaskFilter *)makeTableTable:(KMMYCKotlinByteArray *)table __attribute__((swift_name("makeTable(table:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathEffect.Companion")))
@interface KMMYCSkikoPathEffectCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoPathEffectCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoPathEffect *)makeCornerRadius:(float)radius __attribute__((swift_name("makeCorner(radius:)")));
- (KMMYCSkikoPathEffect *)makeDashIntervals:(KMMYCKotlinFloatArray *)intervals phase:(float)phase __attribute__((swift_name("makeDash(intervals:phase:)")));
- (KMMYCSkikoPathEffect *)makeDiscreteSegLength:(float)segLength dev:(float)dev seed:(int32_t)seed __attribute__((swift_name("makeDiscrete(segLength:dev:seed:)")));
- (KMMYCSkikoPathEffect *)makeLine2DWidth:(float)width matrix:(KMMYCSkikoMatrix33 *)matrix __attribute__((swift_name("makeLine2D(width:matrix:)")));
- (KMMYCSkikoPathEffect *)makePath1DPath:(KMMYCSkikoPath *)path advance:(float)advance phase:(float)phase style:(KMMYCSkikoPathEffectStyle *)style __attribute__((swift_name("makePath1D(path:advance:phase:style:)")));
- (KMMYCSkikoPathEffect *)makePath2DMatrix:(KMMYCSkikoMatrix33 *)matrix path:(KMMYCSkikoPath *)path __attribute__((swift_name("makePath2D(matrix:path:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoISize")))
@interface KMMYCSkikoISize : KMMYCBase
@property (class, readonly, getter=companion) KMMYCSkikoISizeCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)area __attribute__((swift_name("area()")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (BOOL)isZero __attribute__((swift_name("isZero()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoGradientStyle")))
@interface KMMYCSkikoGradientStyle : KMMYCBase
- (instancetype)initWithTileMode:(KMMYCSkikoFilterTileMode *)tileMode isPremul:(BOOL)isPremul localMatrix:(KMMYCSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("init(tileMode:isPremul:localMatrix:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoGradientStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (KMMYCSkikoGradientStyle *)withLocalMatrix_localMatrix:(KMMYCSkikoMatrix33 *)_localMatrix __attribute__((swift_name("withLocalMatrix(_localMatrix:)")));
- (KMMYCSkikoGradientStyle *)withPremul_premul:(BOOL)_premul __attribute__((swift_name("withPremul(_premul:)")));
- (KMMYCSkikoGradientStyle *)withTileMode_tileMode:(KMMYCSkikoFilterTileMode *)_tileMode __attribute__((swift_name("withTileMode(_tileMode:)")));
@property (readonly) BOOL isPremul __attribute__((swift_name("isPremul")));
@property (readonly) KMMYCSkikoMatrix33 * _Nullable localMatrix __attribute__((swift_name("localMatrix")));
@property (readonly) KMMYCSkikoFilterTileMode *tileMode __attribute__((swift_name("tileMode")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinShortArray")))
@interface KMMYCKotlinShortArray : KMMYCBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(KMMYCShort *(^)(KMMYCInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int16_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (KMMYCKotlinShortIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int16_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((swift_name("Ui_textFont")))
@protocol KMMYCUi_textFont
@required
@property (readonly) int32_t loadingStrategy __attribute__((swift_name("loadingStrategy")));
@property (readonly) int32_t style __attribute__((swift_name("style")));
@property (readonly) KMMYCUi_textFontWeight *weight __attribute__((swift_name("weight")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathOp")))
@interface KMMYCSkikoPathOp : KMMYCKotlinEnum<KMMYCSkikoPathOp *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPathOp *difference __attribute__((swift_name("difference")));
@property (class, readonly) KMMYCSkikoPathOp *intersect __attribute__((swift_name("intersect")));
@property (class, readonly) KMMYCSkikoPathOp *union_ __attribute__((swift_name("union_")));
@property (class, readonly) KMMYCSkikoPathOp *xor_ __attribute__((swift_name("xor_")));
@property (class, readonly) KMMYCSkikoPathOp *reverseDifference __attribute__((swift_name("reverseDifference")));
+ (KMMYCKotlinArray<KMMYCSkikoPathOp *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix33.Companion")))
@interface KMMYCSkikoMatrix33Companion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoMatrix33Companion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoMatrix33 *)makeRotateDeg:(float)deg __attribute__((swift_name("makeRotate(deg:)")));
- (KMMYCSkikoMatrix33 *)makeRotateDeg:(float)deg pivotx:(float)pivotx pivoty:(float)pivoty __attribute__((swift_name("makeRotate(deg:pivotx:pivoty:)")));
- (KMMYCSkikoMatrix33 *)makeRotateDeg:(float)deg pivot:(KMMYCSkikoPoint *)pivot __attribute__((swift_name("makeRotate(deg:pivot:)")));
- (KMMYCSkikoMatrix33 *)makeScaleS:(float)s __attribute__((swift_name("makeScale(s:)")));
- (KMMYCSkikoMatrix33 *)makeScaleSx:(float)sx sy:(float)sy __attribute__((swift_name("makeScale(sx:sy:)")));
- (KMMYCSkikoMatrix33 *)makeSkewSx:(float)sx sy:(float)sy __attribute__((swift_name("makeSkew(sx:sy:)")));
- (KMMYCSkikoMatrix33 *)makeTranslateDx:(float)dx dy:(float)dy __attribute__((swift_name("makeTranslate(dx:dy:)")));
@property (readonly) KMMYCSkikoMatrix33 *IDENTITY __attribute__((swift_name("IDENTITY")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix44")))
@interface KMMYCSkikoMatrix44 : KMMYCBase
- (instancetype)initWithMat:(KMMYCKotlinFloatArray *)mat __attribute__((swift_name("init(mat:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoMatrix44Companion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoMatrix33 *)asMatrix33 __attribute__((swift_name("asMatrix33()")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCKotlinFloatArray *mat __attribute__((swift_name("mat")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPoint.Companion")))
@interface KMMYCSkikoPointCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoPointCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCKotlinFloatArray * _Nullable)flattenArrayPts:(KMMYCKotlinArray<KMMYCSkikoPoint *> * _Nullable)pts __attribute__((swift_name("flattenArray(pts:)")));
- (KMMYCKotlinArray<KMMYCSkikoPoint *> * _Nullable)fromArrayPts:(KMMYCKotlinFloatArray * _Nullable)pts __attribute__((swift_name("fromArray(pts:)")));
@property (readonly) KMMYCSkikoPoint *ZERO __attribute__((swift_name("ZERO")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRRect.Companion")))
@interface KMMYCSkikoRRectCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoRRectCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRRect *)makeComplexLTRBL:(float)l t:(float)t r:(float)r b:(float)b radii:(KMMYCKotlinFloatArray *)radii __attribute__((swift_name("makeComplexLTRB(l:t:r:b:radii:)")));
- (KMMYCSkikoRRect *)makeComplexXYWHL:(float)l t:(float)t w:(float)w h:(float)h radii:(KMMYCKotlinFloatArray *)radii __attribute__((swift_name("makeComplexXYWH(l:t:w:h:radii:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRRect *)makeLTRBL:(float)l t:(float)t r:(float)r b:(float)b radius:(float)radius __attribute__((swift_name("makeLTRB(l:t:r:b:radius:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRRect *)makeLTRBL:(float)l t:(float)t r:(float)r b:(float)b xRad:(float)xRad yRad:(float)yRad __attribute__((swift_name("makeLTRB(l:t:r:b:xRad:yRad:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRRect *)makeLTRBL:(float)l t:(float)t r:(float)r b:(float)b tlRad:(float)tlRad trRad:(float)trRad brRad:(float)brRad blRad:(float)blRad __attribute__((swift_name("makeLTRB(l:t:r:b:tlRad:trRad:brRad:blRad:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoRRect *)makeNinePatchLTRBL:(float)l t:(float)t r:(float)r b:(float)b lRad:(float)lRad tRad:(float)tRad rRad:(float)rRad bRad:(float)bRad __attribute__((swift_name("makeNinePatchLTRB(l:t:r:b:lRad:tRad:rRad:bRad:)")));
- (KMMYCSkikoRRect *)makeNinePatchXYWHL:(float)l t:(float)t w:(float)w h:(float)h lRad:(float)lRad tRad:(float)tRad rRad:(float)rRad bRad:(float)bRad __attribute__((swift_name("makeNinePatchXYWH(l:t:w:h:lRad:tRad:rRad:bRad:)")));
- (KMMYCSkikoRRect *)makeOvalLTRBL:(float)l t:(float)t r:(float)r b:(float)b __attribute__((swift_name("makeOvalLTRB(l:t:r:b:)")));
- (KMMYCSkikoRRect *)makeOvalXYWHL:(float)l t:(float)t w:(float)w h:(float)h __attribute__((swift_name("makeOvalXYWH(l:t:w:h:)")));
- (KMMYCSkikoRRect *)makePillLTRBL:(float)l t:(float)t r:(float)r b:(float)b __attribute__((swift_name("makePillLTRB(l:t:r:b:)")));
- (KMMYCSkikoRRect *)makePillXYWHL:(float)l t:(float)t w:(float)w h:(float)h __attribute__((swift_name("makePillXYWH(l:t:w:h:)")));
- (KMMYCSkikoRRect *)makeXYWHL:(float)l t:(float)t w:(float)w h:(float)h radius:(float)radius __attribute__((swift_name("makeXYWH(l:t:w:h:radius:)")));
- (KMMYCSkikoRRect *)makeXYWHL:(float)l t:(float)t w:(float)w h:(float)h xRad:(float)xRad yRad:(float)yRad __attribute__((swift_name("makeXYWH(l:t:w:h:xRad:yRad:)")));
- (KMMYCSkikoRRect *)makeXYWHL:(float)l t:(float)t w:(float)w h:(float)h tlRad:(float)tlRad trRad:(float)trRad brRad:(float)brRad blRad:(float)blRad __attribute__((swift_name("makeXYWH(l:t:w:h:tlRad:trRad:brRad:blRad:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathSegmentIterator.Companion")))
@interface KMMYCSkikoPathSegmentIteratorCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoPathSegmentIteratorCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoPathSegmentIterator *)makePath:(KMMYCSkikoPath * _Nullable)path forceClose:(BOOL)forceClose __attribute__((swift_name("make(path:forceClose:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathSegment")))
@interface KMMYCSkikoPathSegment : KMMYCBase
- (instancetype)initWithVerbOrdinal:(int32_t)verbOrdinal x0:(float)x0 y0:(float)y0 isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(verbOrdinal:x0:y0:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 isCloseLine:(BOOL)isCloseLine isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(x0:y0:x1:y1:isCloseLine:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(x0:y0:x1:y1:x2:y2:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 conicWeight:(float)conicWeight isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(x0:y0:x1:y1:x2:y2:conicWeight:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 x3:(float)x3 y3:(float)y3 isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(x0:y0:x1:y1:x2:y2:x3:y3:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithVerb:(KMMYCSkikoPathVerb *)verb p0:(KMMYCSkikoPoint * _Nullable)p0 p1:(KMMYCSkikoPoint * _Nullable)p1 p2:(KMMYCSkikoPoint * _Nullable)p2 p3:(KMMYCSkikoPoint * _Nullable)p3 conicWeight:(float)conicWeight isCloseLine:(BOOL)isCloseLine isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(verb:p0:p1:p2:p3:conicWeight:isCloseLine:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float conicWeight __attribute__((swift_name("conicWeight")));
@property (readonly) BOOL isCloseLine __attribute__((swift_name("isCloseLine")));
@property (readonly) BOOL isClosedContour __attribute__((swift_name("isClosedContour")));
@property (readonly) KMMYCSkikoPoint * _Nullable p0 __attribute__((swift_name("p0")));
@property (readonly) KMMYCSkikoPoint * _Nullable p1 __attribute__((swift_name("p1")));
@property (readonly) KMMYCSkikoPoint * _Nullable p2 __attribute__((swift_name("p2")));
@property (readonly) KMMYCSkikoPoint * _Nullable p3 __attribute__((swift_name("p3")));
@property (readonly) KMMYCSkikoPathVerb *verb __attribute__((swift_name("verb")));
@end

__attribute__((swift_name("KotlinByteIterator")))
@interface KMMYCKotlinByteIterator : KMMYCBase <KMMYCKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (KMMYCByte *)next __attribute__((swift_name("next()")));
- (int8_t)nextByte __attribute__((swift_name("nextByte()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoIRect.Companion")))
@interface KMMYCSkikoIRectCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoIRectCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoIRect *)makeLTRBL:(int32_t)l t:(int32_t)t r:(int32_t)r b:(int32_t)b __attribute__((swift_name("makeLTRB(l:t:r:b:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoIRect *)makeWHW:(int32_t)w h:(int32_t)h __attribute__((swift_name("makeWH(w:h:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoIRect *)makeXYWHL:(int32_t)l t:(int32_t)t w:(int32_t)w h:(int32_t)h __attribute__((swift_name("makeXYWH(l:t:w:h:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoIPoint")))
@interface KMMYCSkikoIPoint : KMMYCBase
- (instancetype)initWithX:(int32_t)x y:(int32_t)y __attribute__((swift_name("init(x:y:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoIPointCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (KMMYCSkikoIPoint *)offsetDx:(int32_t)dx dy:(int32_t)dy __attribute__((swift_name("offset(dx:dy:)")));
- (KMMYCSkikoIPoint *)offsetVec:(KMMYCSkikoIPoint *)vec __attribute__((swift_name("offset(vec:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) int32_t x __attribute__((swift_name("x")));
@property (readonly) int32_t y __attribute__((swift_name("y")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorMatrix")))
@interface KMMYCSkikoColorMatrix : KMMYCBase
- (instancetype)initWithMat:(KMMYCKotlinFloatArray *)mat __attribute__((swift_name("init(mat:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCKotlinFloatArray *mat __attribute__((swift_name("mat")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoInversionMode")))
@interface KMMYCSkikoInversionMode : KMMYCKotlinEnum<KMMYCSkikoInversionMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoInversionMode *no __attribute__((swift_name("no")));
@property (class, readonly) KMMYCSkikoInversionMode *brightness __attribute__((swift_name("brightness")));
@property (class, readonly) KMMYCSkikoInversionMode *lightness __attribute__((swift_name("lightness")));
+ (KMMYCKotlinArray<KMMYCSkikoInversionMode *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRegion")))
@interface KMMYCSkikoRegion : KMMYCSkikoManaged
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoRegionCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)computeRegionComplexity __attribute__((swift_name("computeRegionComplexity()")));
- (BOOL)containsX:(int32_t)x y:(int32_t)y __attribute__((swift_name("contains(x:y:)")));
- (BOOL)containsRect:(KMMYCSkikoIRect *)rect __attribute__((swift_name("contains(rect:)")));
- (BOOL)containsR:(KMMYCSkikoRegion * _Nullable)r __attribute__((swift_name("contains(r:)")));
- (BOOL)getBoundaryPathP:(KMMYCSkikoPath * _Nullable)p __attribute__((swift_name("getBoundaryPath(p:)")));
- (BOOL)intersectsRect:(KMMYCSkikoIRect *)rect __attribute__((swift_name("intersects(rect:)")));
- (BOOL)intersectsR:(KMMYCSkikoRegion * _Nullable)r __attribute__((swift_name("intersects(r:)")));
- (BOOL)opRect:(KMMYCSkikoIRect *)rect op:(KMMYCSkikoRegionOp *)op __attribute__((swift_name("op(rect:op:)")));
- (BOOL)opRect:(KMMYCSkikoIRect *)rect r:(KMMYCSkikoRegion * _Nullable)r op:(KMMYCSkikoRegionOp *)op __attribute__((swift_name("op(rect:r:op:)")));
- (BOOL)opR:(KMMYCSkikoRegion * _Nullable)r rect:(KMMYCSkikoIRect *)rect op:(KMMYCSkikoRegionOp *)op __attribute__((swift_name("op(r:rect:op:)")));
- (BOOL)opR:(KMMYCSkikoRegion * _Nullable)r op:(KMMYCSkikoRegionOp *)op __attribute__((swift_name("op(r:op:)")));
- (BOOL)opA:(KMMYCSkikoRegion * _Nullable)a b:(KMMYCSkikoRegion * _Nullable)b op:(KMMYCSkikoRegionOp *)op __attribute__((swift_name("op(a:b:op:)")));
- (BOOL)quickContainsRect:(KMMYCSkikoIRect *)rect __attribute__((swift_name("quickContains(rect:)")));
- (BOOL)quickRejectRect:(KMMYCSkikoIRect *)rect __attribute__((swift_name("quickReject(rect:)")));
- (BOOL)quickRejectR:(KMMYCSkikoRegion * _Nullable)r __attribute__((swift_name("quickReject(r:)")));
- (BOOL)setR:(KMMYCSkikoRegion * _Nullable)r __attribute__((swift_name("set(r:)")));
- (BOOL)setEmpty __attribute__((swift_name("setEmpty()")));
- (BOOL)setPathPath:(KMMYCSkikoPath * _Nullable)path clip:(KMMYCSkikoRegion * _Nullable)clip __attribute__((swift_name("setPath(path:clip:)")));
- (BOOL)setRectRect:(KMMYCSkikoIRect *)rect __attribute__((swift_name("setRect(rect:)")));
- (BOOL)setRectsRects:(KMMYCKotlinArray<KMMYCSkikoIRect *> *)rects __attribute__((swift_name("setRects(rects:)")));
- (BOOL)setRegionR:(KMMYCSkikoRegion * _Nullable)r __attribute__((swift_name("setRegion(r:)")));
- (void)translateDx:(int32_t)dx dy:(int32_t)dy __attribute__((swift_name("translate(dx:dy:)")));
@property (readonly) KMMYCSkikoIRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) BOOL isComplex __attribute__((swift_name("isComplex")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) BOOL isRect __attribute__((swift_name("isRect")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFilterTileMode")))
@interface KMMYCSkikoFilterTileMode : KMMYCKotlinEnum<KMMYCSkikoFilterTileMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoFilterTileMode *clamp __attribute__((swift_name("clamp")));
@property (class, readonly) KMMYCSkikoFilterTileMode *repeat __attribute__((swift_name("repeat")));
@property (class, readonly) KMMYCSkikoFilterTileMode *mirror __attribute__((swift_name("mirror")));
@property (class, readonly) KMMYCSkikoFilterTileMode *decal __attribute__((swift_name("decal")));
+ (KMMYCKotlinArray<KMMYCSkikoFilterTileMode *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorChannel")))
@interface KMMYCSkikoColorChannel : KMMYCKotlinEnum<KMMYCSkikoColorChannel *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoColorChannel *r __attribute__((swift_name("r")));
@property (class, readonly) KMMYCSkikoColorChannel *g __attribute__((swift_name("g")));
@property (class, readonly) KMMYCSkikoColorChannel *b __attribute__((swift_name("b")));
@property (class, readonly) KMMYCSkikoColorChannel *a __attribute__((swift_name("a")));
+ (KMMYCKotlinArray<KMMYCSkikoColorChannel *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((swift_name("SkikoIHasImageInfo")))
@protocol KMMYCSkikoIHasImageInfo
@required
@property (readonly) KMMYCSkikoColorAlphaType *alphaType __attribute__((swift_name("alphaType")));
@property (readonly) int32_t bytesPerPixel __attribute__((swift_name("bytesPerPixel")));
@property (readonly) KMMYCSkikoColorInfo *colorInfo __attribute__((swift_name("colorInfo")));
@property (readonly) KMMYCSkikoColorSpace * _Nullable colorSpace __attribute__((swift_name("colorSpace")));
@property (readonly) KMMYCSkikoColorType *colorType __attribute__((swift_name("colorType")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) KMMYCSkikoImageInfo *imageInfo __attribute__((swift_name("imageInfo")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) BOOL isOpaque __attribute__((swift_name("isOpaque")));
@property (readonly) int32_t shiftPerPixel __attribute__((swift_name("shiftPerPixel")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImage")))
@interface KMMYCSkikoImage : KMMYCSkikoRefCnt <KMMYCSkikoIHasImageInfo>

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoImageCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoData * _Nullable)encodeToDataFormat:(KMMYCSkikoEncodedImageFormat *)format quality:(int32_t)quality __attribute__((swift_name("encodeToData(format:quality:)")));
- (KMMYCSkikoShader *)makeShaderTmx:(KMMYCSkikoFilterTileMode *)tmx tmy:(KMMYCSkikoFilterTileMode *)tmy localMatrix:(KMMYCSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(tmx:tmy:localMatrix:)")));
- (KMMYCSkikoShader *)makeShaderTmx:(KMMYCSkikoFilterTileMode *)tmx tmy:(KMMYCSkikoFilterTileMode *)tmy sampling:(id<KMMYCSkikoSamplingMode>)sampling localMatrix:(KMMYCSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(tmx:tmy:sampling:localMatrix:)")));
- (KMMYCSkikoShader *)makeShaderLocalMatrix:(KMMYCSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(localMatrix:)")));
- (KMMYCSkikoPixmap * _Nullable)peekPixels __attribute__((swift_name("peekPixels()")));
- (BOOL)peekPixelsPixmap:(KMMYCSkikoPixmap * _Nullable)pixmap __attribute__((swift_name("peekPixels(pixmap:)")));
- (BOOL)readPixelsDst:(KMMYCSkikoBitmap *)dst __attribute__((swift_name("readPixels(dst:)")));
- (BOOL)readPixelsDst:(KMMYCSkikoBitmap *)dst srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(dst:srcX:srcY:)")));
- (BOOL)readPixelsContext:(KMMYCSkikoDirectContext *)context dst:(KMMYCSkikoBitmap *)dst __attribute__((swift_name("readPixels(context:dst:)")));
- (BOOL)readPixelsContext:(KMMYCSkikoDirectContext *)context dst:(KMMYCSkikoBitmap *)dst srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(context:dst:srcX:srcY:)")));
- (BOOL)readPixelsContext:(KMMYCSkikoDirectContext * _Nullable)context dst:(KMMYCSkikoBitmap *)dst srcX:(int32_t)srcX srcY:(int32_t)srcY cache:(BOOL)cache __attribute__((swift_name("readPixels(context:dst:srcX:srcY:cache:)")));
- (BOOL)readPixelsDst:(KMMYCSkikoPixmap *)dst srcX:(int32_t)srcX srcY:(int32_t)srcY cache:(BOOL)cache __attribute__((swift_name("readPixels(dst:srcX:srcY:cache:)")));
- (BOOL)scalePixelsDst:(KMMYCSkikoPixmap *)dst samplingMode:(id<KMMYCSkikoSamplingMode>)samplingMode cache:(BOOL)cache __attribute__((swift_name("scalePixels(dst:samplingMode:cache:)")));
@property (readonly) KMMYCSkikoImageInfo *imageInfo __attribute__((swift_name("imageInfo")));
@end

__attribute__((swift_name("SkikoSamplingMode")))
@protocol KMMYCSkikoSamplingMode
@required
- (int64_t)_pack __attribute__((swift_name("_pack()"))) __attribute__((deprecated("Long can't be used because Long is an object in kotlin/js. Consider using _packedInt1 and _packedInt2")));
- (int32_t)_packedInt1 __attribute__((swift_name("_packedInt1()")));
- (int32_t)_packedInt2 __attribute__((swift_name("_packedInt2()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRuntimeShaderBuilder")))
@interface KMMYCSkikoRuntimeShaderBuilder : KMMYCSkikoManaged
- (instancetype)initWithEffect:(KMMYCSkikoRuntimeEffect *)effect __attribute__((swift_name("init(effect:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoRuntimeShaderBuilderCompanion *companion __attribute__((swift_name("companion")));
- (void)childName:(NSString *)name colorFilter:(KMMYCSkikoColorFilter *)colorFilter __attribute__((swift_name("child(name:colorFilter:)")));
- (void)childName:(NSString *)name shader:(KMMYCSkikoShader *)shader __attribute__((swift_name("child(name:shader:)")));
- (void)uniformName:(NSString *)name value:(float)value __attribute__((swift_name("uniform(name:value:)")));
- (void)uniformName:(NSString *)name value1:(float)value1 value2:(float)value2 __attribute__((swift_name("uniform(name:value1:value2:)")));
- (void)uniformName:(NSString *)name value1:(float)value1 value2:(float)value2 value3:(float)value3 __attribute__((swift_name("uniform(name:value1:value2:value3:)")));
- (void)uniformName:(NSString *)name value1:(float)value1 value2:(float)value2 value3:(float)value3 value4:(float)value4 __attribute__((swift_name("uniform(name:value1:value2:value3:value4:)")));
- (void)uniformName:(NSString *)name value_:(int32_t)value __attribute__((swift_name("uniform(name:value_:)")));
- (void)uniformName:(NSString *)name value1:(int32_t)value1 value2_:(int32_t)value2 __attribute__((swift_name("uniform(name:value1:value2_:)")));
- (void)uniformName:(NSString *)name value1:(int32_t)value1 value2:(int32_t)value2 value3_:(int32_t)value3 __attribute__((swift_name("uniform(name:value1:value2:value3_:)")));
- (void)uniformName:(NSString *)name value1:(int32_t)value1 value2:(int32_t)value2 value3:(int32_t)value3 value4_:(int32_t)value4 __attribute__((swift_name("uniform(name:value1:value2:value3:value4_:)")));
- (void)uniformName:(NSString *)name value__:(KMMYCSkikoMatrix22 *)value __attribute__((swift_name("uniform(name:value__:)")));
- (void)uniformName:(NSString *)name value___:(KMMYCSkikoMatrix33 *)value __attribute__((swift_name("uniform(name:value___:)")));
- (void)uniformName:(NSString *)name value____:(KMMYCSkikoMatrix44 *)value __attribute__((swift_name("uniform(name:value____:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFilterBlurMode")))
@interface KMMYCSkikoFilterBlurMode : KMMYCKotlinEnum<KMMYCSkikoFilterBlurMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoFilterBlurMode *normal __attribute__((swift_name("normal")));
@property (class, readonly) KMMYCSkikoFilterBlurMode *solid __attribute__((swift_name("solid")));
@property (class, readonly) KMMYCSkikoFilterBlurMode *outer __attribute__((swift_name("outer")));
@property (class, readonly) KMMYCSkikoFilterBlurMode *inner __attribute__((swift_name("inner")));
+ (KMMYCKotlinArray<KMMYCSkikoFilterBlurMode *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathEffect.Style")))
@interface KMMYCSkikoPathEffectStyle : KMMYCKotlinEnum<KMMYCSkikoPathEffectStyle *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoPathEffectStyle *translate __attribute__((swift_name("translate")));
@property (class, readonly) KMMYCSkikoPathEffectStyle *rotate __attribute__((swift_name("rotate")));
@property (class, readonly) KMMYCSkikoPathEffectStyle *morph __attribute__((swift_name("morph")));
+ (KMMYCKotlinArray<KMMYCSkikoPathEffectStyle *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoISize.Companion")))
@interface KMMYCSkikoISizeCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoISizeCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoISize *)makeW:(int32_t)w h:(int32_t)h __attribute__((swift_name("make(w:h:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (KMMYCSkikoISize *)makeEmpty __attribute__((swift_name("makeEmpty()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoGradientStyle.Companion")))
@interface KMMYCSkikoGradientStyleCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoGradientStyleCompanion *shared __attribute__((swift_name("shared")));
@property KMMYCSkikoGradientStyle *DEFAULT __attribute__((swift_name("DEFAULT")));
@end

__attribute__((swift_name("KotlinShortIterator")))
@interface KMMYCKotlinShortIterator : KMMYCBase <KMMYCKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (KMMYCShort *)next __attribute__((swift_name("next()")));
- (int16_t)nextShort __attribute__((swift_name("nextShort()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix44.Companion")))
@interface KMMYCSkikoMatrix44Companion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoMatrix44Companion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCSkikoMatrix44 *IDENTITY __attribute__((swift_name("IDENTITY")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoIPoint.Companion")))
@interface KMMYCSkikoIPointCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoIPointCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCSkikoIPoint *ZERO __attribute__((swift_name("ZERO")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRegion.Companion")))
@interface KMMYCSkikoRegionCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoRegionCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRegion.Op")))
@interface KMMYCSkikoRegionOp : KMMYCKotlinEnum<KMMYCSkikoRegionOp *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoRegionOpCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) KMMYCSkikoRegionOp *difference __attribute__((swift_name("difference")));
@property (class, readonly) KMMYCSkikoRegionOp *intersect __attribute__((swift_name("intersect")));
@property (class, readonly) KMMYCSkikoRegionOp *union_ __attribute__((swift_name("union_")));
@property (class, readonly) KMMYCSkikoRegionOp *xor_ __attribute__((swift_name("xor_")));
@property (class, readonly) KMMYCSkikoRegionOp *reverseDifference __attribute__((swift_name("reverseDifference")));
@property (class, readonly) KMMYCSkikoRegionOp *replace __attribute__((swift_name("replace")));
+ (KMMYCKotlinArray<KMMYCSkikoRegionOp *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorAlphaType")))
@interface KMMYCSkikoColorAlphaType : KMMYCKotlinEnum<KMMYCSkikoColorAlphaType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoColorAlphaType *unknown __attribute__((swift_name("unknown")));
@property (class, readonly) KMMYCSkikoColorAlphaType *opaque __attribute__((swift_name("opaque")));
@property (class, readonly) KMMYCSkikoColorAlphaType *premul __attribute__((swift_name("premul")));
@property (class, readonly) KMMYCSkikoColorAlphaType *unpremul __attribute__((swift_name("unpremul")));
+ (KMMYCKotlinArray<KMMYCSkikoColorAlphaType *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorInfo")))
@interface KMMYCSkikoColorInfo : KMMYCBase
- (instancetype)initWithColorType:(KMMYCSkikoColorType *)colorType alphaType:(KMMYCSkikoColorAlphaType *)alphaType colorSpace:(KMMYCSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("init(colorType:alphaType:colorSpace:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoColorInfoCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (KMMYCSkikoColorInfo *)withAlphaType_alphaType:(KMMYCSkikoColorAlphaType *)_alphaType __attribute__((swift_name("withAlphaType(_alphaType:)")));
- (KMMYCSkikoColorInfo *)withColorSpace_colorSpace:(KMMYCSkikoColorSpace * _Nullable)_colorSpace __attribute__((swift_name("withColorSpace(_colorSpace:)")));
- (KMMYCSkikoColorInfo *)withColorType_colorType:(KMMYCSkikoColorType *)_colorType __attribute__((swift_name("withColorType(_colorType:)")));
@property (readonly) KMMYCSkikoColorAlphaType *alphaType __attribute__((swift_name("alphaType")));
@property (readonly) int32_t bytesPerPixel __attribute__((swift_name("bytesPerPixel")));
@property (readonly) KMMYCSkikoColorSpace * _Nullable colorSpace __attribute__((swift_name("colorSpace")));
@property (readonly) KMMYCSkikoColorType *colorType __attribute__((swift_name("colorType")));
@property (readonly) BOOL isGammaCloseToSRGB __attribute__((swift_name("isGammaCloseToSRGB")));
@property (readonly) BOOL isOpaque __attribute__((swift_name("isOpaque")));
@property (readonly) int32_t shiftPerPixel __attribute__((swift_name("shiftPerPixel")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorType")))
@interface KMMYCSkikoColorType : KMMYCKotlinEnum<KMMYCSkikoColorType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoColorTypeCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) KMMYCSkikoColorType *unknown __attribute__((swift_name("unknown")));
@property (class, readonly) KMMYCSkikoColorType *alpha8 __attribute__((swift_name("alpha8")));
@property (class, readonly) KMMYCSkikoColorType *rgb565 __attribute__((swift_name("rgb565")));
@property (class, readonly) KMMYCSkikoColorType *argb4444 __attribute__((swift_name("argb4444")));
@property (class, readonly) KMMYCSkikoColorType *rgba8888 __attribute__((swift_name("rgba8888")));
@property (class, readonly) KMMYCSkikoColorType *rgb888x __attribute__((swift_name("rgb888x")));
@property (class, readonly) KMMYCSkikoColorType *bgra8888 __attribute__((swift_name("bgra8888")));
@property (class, readonly) KMMYCSkikoColorType *rgba1010102 __attribute__((swift_name("rgba1010102")));
@property (class, readonly) KMMYCSkikoColorType *bgra1010102 __attribute__((swift_name("bgra1010102")));
@property (class, readonly) KMMYCSkikoColorType *rgb101010x __attribute__((swift_name("rgb101010x")));
@property (class, readonly) KMMYCSkikoColorType *bgr101010x __attribute__((swift_name("bgr101010x")));
@property (class, readonly) KMMYCSkikoColorType *gray8 __attribute__((swift_name("gray8")));
@property (class, readonly) KMMYCSkikoColorType *rgbaF16norm __attribute__((swift_name("rgbaF16norm")));
@property (class, readonly) KMMYCSkikoColorType *rgbaF16 __attribute__((swift_name("rgbaF16")));
@property (class, readonly) KMMYCSkikoColorType *rgbaF32 __attribute__((swift_name("rgbaF32")));
@property (class, readonly) KMMYCSkikoColorType *r8g8Unorm __attribute__((swift_name("r8g8Unorm")));
@property (class, readonly) KMMYCSkikoColorType *a16Float __attribute__((swift_name("a16Float")));
@property (class, readonly) KMMYCSkikoColorType *r16g16Float __attribute__((swift_name("r16g16Float")));
@property (class, readonly) KMMYCSkikoColorType *a16Unorm __attribute__((swift_name("a16Unorm")));
@property (class, readonly) KMMYCSkikoColorType *r16g16Unorm __attribute__((swift_name("r16g16Unorm")));
@property (class, readonly) KMMYCSkikoColorType *r16g16b16a16Unorm __attribute__((swift_name("r16g16b16a16Unorm")));
+ (KMMYCKotlinArray<KMMYCSkikoColorType *> *)values __attribute__((swift_name("values()")));
- (int64_t)computeOffsetX:(int32_t)x y:(int32_t)y rowBytes:(int64_t)rowBytes __attribute__((swift_name("computeOffset(x:y:rowBytes:)")));
- (float)getAColor:(int8_t)color __attribute__((swift_name("getA(color:)")));
- (float)getAColor_:(int32_t)color __attribute__((swift_name("getA(color_:)")));
- (float)getAColor__:(int16_t)color __attribute__((swift_name("getA(color__:)")));
- (float)getBColor:(int8_t)color __attribute__((swift_name("getB(color:)")));
- (float)getBColor_:(int32_t)color __attribute__((swift_name("getB(color_:)")));
- (float)getBColor__:(int16_t)color __attribute__((swift_name("getB(color__:)")));
- (float)getGColor:(int8_t)color __attribute__((swift_name("getG(color:)")));
- (float)getGColor_:(int32_t)color __attribute__((swift_name("getG(color_:)")));
- (float)getGColor__:(int16_t)color __attribute__((swift_name("getG(color__:)")));
- (float)getRColor:(int8_t)color __attribute__((swift_name("getR(color:)")));
- (float)getRColor_:(int32_t)color __attribute__((swift_name("getR(color_:)")));
- (float)getRColor__:(int16_t)color __attribute__((swift_name("getR(color__:)")));
- (KMMYCSkikoColorAlphaType * _Nullable)validateAlphaTypeAlphaType:(KMMYCSkikoColorAlphaType *)alphaType __attribute__((swift_name("validateAlphaType(alphaType:)")));
@property (readonly) int32_t bytesPerPixel __attribute__((swift_name("bytesPerPixel")));
@property (readonly) BOOL isAlwaysOpaque __attribute__((swift_name("isAlwaysOpaque")));
@property (readonly) int32_t shiftPerPixel __attribute__((swift_name("shiftPerPixel")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImageInfo")))
@interface KMMYCSkikoImageInfo : KMMYCBase
- (instancetype)initWithWidth:(int32_t)width height:(int32_t)height colorType:(KMMYCSkikoColorType *)colorType alphaType:(KMMYCSkikoColorAlphaType *)alphaType __attribute__((swift_name("init(width:height:colorType:alphaType:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithWidth:(int32_t)width height:(int32_t)height colorType:(KMMYCSkikoColorType *)colorType alphaType:(KMMYCSkikoColorAlphaType *)alphaType colorSpace:(KMMYCSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("init(width:height:colorType:alphaType:colorSpace:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithColorInfo:(KMMYCSkikoColorInfo *)colorInfo width:(int32_t)width height:(int32_t)height __attribute__((swift_name("init(colorInfo:width:height:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoImageInfoCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)computeByteSizeRowBytes:(int32_t)rowBytes __attribute__((swift_name("computeByteSize(rowBytes:)")));
- (int32_t)computeMinByteSize __attribute__((swift_name("computeMinByteSize()")));
- (int64_t)computeOffsetX:(int32_t)x y:(int32_t)y rowBytes:(int64_t)rowBytes __attribute__((swift_name("computeOffset(x:y:rowBytes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isRowBytesValidRowBytes:(int64_t)rowBytes __attribute__((swift_name("isRowBytesValid(rowBytes:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (KMMYCSkikoImageInfo *)withColorAlphaTypeAlphaType:(KMMYCSkikoColorAlphaType *)alphaType __attribute__((swift_name("withColorAlphaType(alphaType:)")));
- (KMMYCSkikoImageInfo *)withColorInfo_colorInfo:(KMMYCSkikoColorInfo *)_colorInfo __attribute__((swift_name("withColorInfo(_colorInfo:)")));
- (KMMYCSkikoImageInfo *)withColorSpaceColorSpace:(KMMYCSkikoColorSpace *)colorSpace __attribute__((swift_name("withColorSpace(colorSpace:)")));
- (KMMYCSkikoImageInfo *)withColorTypeColorType:(KMMYCSkikoColorType *)colorType __attribute__((swift_name("withColorType(colorType:)")));
- (KMMYCSkikoImageInfo *)withHeight_height:(int32_t)_height __attribute__((swift_name("withHeight(_height:)")));
- (KMMYCSkikoImageInfo *)withWidth_width:(int32_t)_width __attribute__((swift_name("withWidth(_width:)")));
- (KMMYCSkikoImageInfo *)withWidthHeightWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("withWidthHeight(width:height:)")));
@property (readonly) KMMYCSkikoIRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) int32_t bytesPerPixel __attribute__((swift_name("bytesPerPixel")));
@property (readonly) KMMYCSkikoColorAlphaType *colorAlphaType __attribute__((swift_name("colorAlphaType")));
@property (readonly) KMMYCSkikoColorInfo *colorInfo __attribute__((swift_name("colorInfo")));
@property (readonly) KMMYCSkikoColorSpace * _Nullable colorSpace __attribute__((swift_name("colorSpace")));
@property (readonly) KMMYCSkikoColorType *colorType __attribute__((swift_name("colorType")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) BOOL isGammaCloseToSRGB __attribute__((swift_name("isGammaCloseToSRGB")));
@property (readonly) BOOL isOpaque __attribute__((swift_name("isOpaque")));
@property (readonly) int32_t minRowBytes __attribute__((swift_name("minRowBytes")));
@property (readonly) int32_t shiftPerPixel __attribute__((swift_name("shiftPerPixel")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImage.Companion")))
@interface KMMYCSkikoImageCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoImageCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoImage *)makeFromBitmapBitmap:(KMMYCSkikoBitmap *)bitmap __attribute__((swift_name("makeFromBitmap(bitmap:)")));
- (KMMYCSkikoImage *)makeFromEncodedBytes:(KMMYCKotlinByteArray *)bytes __attribute__((swift_name("makeFromEncoded(bytes:)")));
- (KMMYCSkikoImage *)makeFromPixmapPixmap:(KMMYCSkikoPixmap *)pixmap __attribute__((swift_name("makeFromPixmap(pixmap:)")));
- (KMMYCSkikoImage *)makeRasterImageInfo:(KMMYCSkikoImageInfo *)imageInfo bytes:(KMMYCKotlinByteArray *)bytes rowBytes:(int32_t)rowBytes __attribute__((swift_name("makeRaster(imageInfo:bytes:rowBytes:)")));
- (KMMYCSkikoImage *)makeRasterImageInfo:(KMMYCSkikoImageInfo *)imageInfo data:(KMMYCSkikoData *)data rowBytes:(int32_t)rowBytes __attribute__((swift_name("makeRaster(imageInfo:data:rowBytes:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoData")))
@interface KMMYCSkikoData : KMMYCSkikoManaged
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoDataCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (KMMYCKotlinByteArray *)getBytesOffset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("getBytes(offset:length:)")));
- (KMMYCSkikoData *)makeCopy __attribute__((swift_name("makeCopy()")));
- (KMMYCSkikoData *)makeSubsetOffset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("makeSubset(offset:length:)")));
- (void * _Nullable)writableData __attribute__((swift_name("writableData()")));
@property (readonly) KMMYCKotlinByteArray *bytes __attribute__((swift_name("bytes")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoEncodedImageFormat")))
@interface KMMYCSkikoEncodedImageFormat : KMMYCKotlinEnum<KMMYCSkikoEncodedImageFormat *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *bmp __attribute__((swift_name("bmp")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *gif __attribute__((swift_name("gif")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *ico __attribute__((swift_name("ico")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *jpeg __attribute__((swift_name("jpeg")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *png __attribute__((swift_name("png")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *wbmp __attribute__((swift_name("wbmp")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *webp __attribute__((swift_name("webp")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *pkm __attribute__((swift_name("pkm")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *ktx __attribute__((swift_name("ktx")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *astc __attribute__((swift_name("astc")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *dng __attribute__((swift_name("dng")));
@property (class, readonly) KMMYCSkikoEncodedImageFormat *heif __attribute__((swift_name("heif")));
+ (KMMYCKotlinArray<KMMYCSkikoEncodedImageFormat *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixmap")))
@interface KMMYCSkikoPixmap : KMMYCSkikoManaged
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoPixmapCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)computeByteSize __attribute__((swift_name("computeByteSize()")));
- (BOOL)computeIsOpaque __attribute__((swift_name("computeIsOpaque()")));
- (BOOL)eraseColor:(int32_t)color __attribute__((swift_name("erase(color:)")));
- (BOOL)eraseColor:(int32_t)color subset:(KMMYCSkikoIRect *)subset __attribute__((swift_name("erase(color:subset:)")));
- (BOOL)extractSubsetSubset:(KMMYCSkikoPixmap *)subset area:(KMMYCSkikoIRect *)area __attribute__((swift_name("extractSubset(subset:area:)")));
- (BOOL)extractSubsetSubsetPtr:(void * _Nullable)subsetPtr area:(KMMYCSkikoIRect *)area __attribute__((swift_name("extractSubset(subsetPtr:area:)")));
- (void * _Nullable)getAddrX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getAddr(x:y:)")));
- (float)getAlphaFX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getAlphaF(x:y:)")));
- (int32_t)getColorX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getColor(x:y:)")));
- (BOOL)readPixelsInfo:(KMMYCSkikoImageInfo *)info addr:(void * _Nullable)addr rowBytes:(int32_t)rowBytes __attribute__((swift_name("readPixels(info:addr:rowBytes:)")));
- (BOOL)readPixelsInfo:(KMMYCSkikoImageInfo *)info addr:(void * _Nullable)addr rowBytes:(int32_t)rowBytes srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(info:addr:rowBytes:srcX:srcY:)")));
- (BOOL)readPixelsPixmap:(KMMYCSkikoPixmap *)pixmap srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(pixmap:srcX:srcY:)")));
- (BOOL)readPixelsPixmap:(KMMYCSkikoPixmap * _Nullable)pixmap __attribute__((swift_name("readPixels(pixmap:)")));
- (void)reset __attribute__((swift_name("reset()")));
- (void)resetInfo:(KMMYCSkikoImageInfo *)info buffer:(KMMYCSkikoData *)buffer rowBytes:(int32_t)rowBytes __attribute__((swift_name("reset(info:buffer:rowBytes:)")));
- (void)resetInfo:(KMMYCSkikoImageInfo *)info addr:(void * _Nullable)addr rowBytes:(int32_t)rowBytes underlyingMemoryOwner:(KMMYCSkikoManaged * _Nullable)underlyingMemoryOwner __attribute__((swift_name("reset(info:addr:rowBytes:underlyingMemoryOwner:)")));
- (BOOL)scalePixelsDstPixmap:(KMMYCSkikoPixmap * _Nullable)dstPixmap samplingMode:(id<KMMYCSkikoSamplingMode>)samplingMode __attribute__((swift_name("scalePixels(dstPixmap:samplingMode:)")));
- (void)setColorSpaceColorSpace:(KMMYCSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("setColorSpace(colorSpace:)")));
@property (readonly) void * _Nullable addr __attribute__((swift_name("addr")));
@property (readonly) KMMYCSkikoData *buffer __attribute__((swift_name("buffer")));
@property (readonly) KMMYCSkikoImageInfo *info __attribute__((swift_name("info")));
@property (readonly) int32_t rowBytes __attribute__((swift_name("rowBytes")));
@property (readonly) int32_t rowBytesAsPixels __attribute__((swift_name("rowBytesAsPixels")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoBitmap")))
@interface KMMYCSkikoBitmap : KMMYCSkikoManaged <KMMYCSkikoIHasImageInfo>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoBitmapCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)doAllocN32PixelsWidth:(int32_t)width height:(int32_t)height opaque:(BOOL)opaque __attribute__((swift_name("doAllocN32Pixels(width:height:opaque:)")));
- (BOOL)doAllocPixels __attribute__((swift_name("doAllocPixels()")));
- (BOOL)doAllocPixelsImageInfo:(KMMYCSkikoImageInfo *)imageInfo __attribute__((swift_name("doAllocPixels(imageInfo:)")));
- (BOOL)doAllocPixelsInfo:(KMMYCSkikoImageInfo *)info rowBytes:(int32_t)rowBytes __attribute__((swift_name("doAllocPixels(info:rowBytes:)")));
- (BOOL)doAllocPixelsFlagsImageInfo:(KMMYCSkikoImageInfo *)imageInfo zeroPixels:(BOOL)zeroPixels __attribute__((swift_name("doAllocPixelsFlags(imageInfo:zeroPixels:)")));
- (int32_t)computeByteSize __attribute__((swift_name("computeByteSize()")));
- (BOOL)computeIsOpaque __attribute__((swift_name("computeIsOpaque()")));
- (BOOL)drawsNothing __attribute__((swift_name("drawsNothing()")));
- (KMMYCSkikoBitmap *)eraseColor:(int32_t)color __attribute__((swift_name("erase(color:)")));
- (KMMYCSkikoBitmap *)eraseColor:(int32_t)color area:(KMMYCSkikoIRect *)area __attribute__((swift_name("erase(color:area:)")));
- (BOOL)extractAlphaDst:(KMMYCSkikoBitmap *)dst __attribute__((swift_name("extractAlpha(dst:)")));
- (KMMYCSkikoIPoint * _Nullable)extractAlphaDst:(KMMYCSkikoBitmap *)dst paint:(KMMYCSkikoPaint * _Nullable)paint __attribute__((swift_name("extractAlpha(dst:paint:)")));
- (BOOL)extractSubsetDst:(KMMYCSkikoBitmap *)dst subset:(KMMYCSkikoIRect *)subset __attribute__((swift_name("extractSubset(dst:subset:)")));
- (float)getAlphafX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getAlphaf(x:y:)")));
- (int32_t)getColorX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getColor(x:y:)")));
- (BOOL)installPixelsPixels:(KMMYCKotlinByteArray * _Nullable)pixels __attribute__((swift_name("installPixels(pixels:)")));
- (BOOL)installPixelsInfo:(KMMYCSkikoImageInfo *)info pixels:(KMMYCKotlinByteArray * _Nullable)pixels rowBytes:(int32_t)rowBytes __attribute__((swift_name("installPixels(info:pixels:rowBytes:)")));
- (KMMYCSkikoBitmap *)makeClone __attribute__((swift_name("makeClone()")));
- (KMMYCSkikoShader *)makeShaderTmx:(KMMYCSkikoFilterTileMode *)tmx tmy:(KMMYCSkikoFilterTileMode *)tmy localMatrix:(KMMYCSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(tmx:tmy:localMatrix:)")));
- (KMMYCSkikoShader *)makeShaderTmx:(KMMYCSkikoFilterTileMode *)tmx tmy:(KMMYCSkikoFilterTileMode *)tmy sampling:(id<KMMYCSkikoSamplingMode>)sampling localMatrix:(KMMYCSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(tmx:tmy:sampling:localMatrix:)")));
- (KMMYCSkikoShader *)makeShaderLocalMatrix:(KMMYCSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(localMatrix:)")));
- (KMMYCSkikoBitmap *)notifyPixelsChanged __attribute__((swift_name("notifyPixelsChanged()")));
- (KMMYCSkikoPixmap * _Nullable)peekPixels __attribute__((swift_name("peekPixels()")));
- (KMMYCKotlinByteArray * _Nullable)readPixelsDstInfo:(KMMYCSkikoImageInfo *)dstInfo dstRowBytes:(int32_t)dstRowBytes srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(dstInfo:dstRowBytes:srcX:srcY:)")));
- (KMMYCSkikoBitmap *)reset __attribute__((swift_name("reset()")));
- (BOOL)setAlphaTypeAlphaType:(KMMYCSkikoColorAlphaType *)alphaType __attribute__((swift_name("setAlphaType(alphaType:)")));
- (BOOL)setImageInfoImageInfo:(KMMYCSkikoImageInfo *)imageInfo __attribute__((swift_name("setImageInfo(imageInfo:)")));
- (BOOL)setImageInfoImageInfo:(KMMYCSkikoImageInfo *)imageInfo rowBytes:(int32_t)rowBytes __attribute__((swift_name("setImageInfo(imageInfo:rowBytes:)")));
- (KMMYCSkikoBitmap *)setImmutable __attribute__((swift_name("setImmutable()")));
- (KMMYCSkikoBitmap *)setPixelRefPixelRef:(KMMYCSkikoPixelRef * _Nullable)pixelRef dx:(int32_t)dx dy:(int32_t)dy __attribute__((swift_name("setPixelRef(pixelRef:dx:dy:)")));
- (void)swapOther:(KMMYCSkikoBitmap *)other __attribute__((swift_name("swap(other:)")));
@property (readonly) KMMYCSkikoIRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) int32_t generationId __attribute__((swift_name("generationId")));
@property (readonly) KMMYCSkikoImageInfo *imageInfo __attribute__((swift_name("imageInfo")));
@property (readonly) BOOL isImmutable __attribute__((swift_name("isImmutable")));
@property (readonly) BOOL isNull __attribute__((swift_name("isNull")));
@property (readonly) BOOL isReadyToDraw __attribute__((swift_name("isReadyToDraw")));
@property (readonly) KMMYCSkikoPixelRef * _Nullable pixelRef __attribute__((swift_name("pixelRef")));
@property (readonly) KMMYCSkikoIPoint *pixelRefOrigin __attribute__((swift_name("pixelRefOrigin")));
@property (readonly) int32_t rowBytes __attribute__((swift_name("rowBytes")));
@property (readonly) int32_t rowBytesAsPixels __attribute__((swift_name("rowBytesAsPixels")));
@property (readonly) KMMYCSkikoIRect *subset __attribute__((swift_name("subset")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoDirectContext")))
@interface KMMYCSkikoDirectContext : KMMYCSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoDirectContextCompanion *companion __attribute__((swift_name("companion")));
- (void)abandon __attribute__((swift_name("abandon()")));
- (KMMYCSkikoDirectContext *)flush __attribute__((swift_name("flush()")));
- (KMMYCSkikoDirectContext *)resetAll __attribute__((swift_name("resetAll()")));
- (KMMYCSkikoDirectContext *)resetGLStates:(KMMYCKotlinArray<KMMYCSkikoGLBackendState *> *)states __attribute__((swift_name("resetGL(states:)")));
- (KMMYCSkikoDirectContext *)resetGLAll __attribute__((swift_name("resetGLAll()")));
- (void)submitSyncCpu:(BOOL)syncCpu __attribute__((swift_name("submit(syncCpu:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRuntimeEffect")))
@interface KMMYCSkikoRuntimeEffect : KMMYCSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoRuntimeEffectCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoShader *)makeShaderUniforms:(KMMYCSkikoData * _Nullable)uniforms children:(KMMYCKotlinArray<KMMYCSkikoShader *> * _Nullable)children localMatrix:(KMMYCSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(uniforms:children:localMatrix:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRuntimeShaderBuilder.Companion")))
@interface KMMYCSkikoRuntimeShaderBuilderCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoRuntimeShaderBuilderCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix22")))
@interface KMMYCSkikoMatrix22 : KMMYCBase
- (instancetype)initWithMat:(KMMYCKotlinFloatArray *)mat __attribute__((swift_name("init(mat:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KMMYCSkikoMatrix22Companion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KMMYCKotlinFloatArray *mat __attribute__((swift_name("mat")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRegion.OpCompanion")))
@interface KMMYCSkikoRegionOpCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoRegionOpCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorInfo.Companion")))
@interface KMMYCSkikoColorInfoCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoColorInfoCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCSkikoColorInfo *DEFAULT __attribute__((swift_name("DEFAULT")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorType.Companion")))
@interface KMMYCSkikoColorTypeCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoColorTypeCompanion *shared __attribute__((swift_name("shared")));
@property KMMYCSkikoColorType *N32 __attribute__((swift_name("N32")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImageInfo.Companion")))
@interface KMMYCSkikoImageInfoCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoImageInfoCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoImageInfo *)createUsing_ptr:(void * _Nullable)_ptr _nGetImageInfo:(void (^)(id _Nullable, id _Nullable, id _Nullable))_nGetImageInfo __attribute__((swift_name("createUsing(_ptr:_nGetImageInfo:)")));
- (KMMYCSkikoImageInfo *)makeA8Width:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeA8(width:height:)")));
- (KMMYCSkikoImageInfo *)makeN32Width:(int32_t)width height:(int32_t)height alphaType:(KMMYCSkikoColorAlphaType *)alphaType __attribute__((swift_name("makeN32(width:height:alphaType:)")));
- (KMMYCSkikoImageInfo *)makeN32Width:(int32_t)width height:(int32_t)height alphaType:(KMMYCSkikoColorAlphaType *)alphaType colorSpace:(KMMYCSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("makeN32(width:height:alphaType:colorSpace:)")));
- (KMMYCSkikoImageInfo *)makeN32PremulWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeN32Premul(width:height:)")));
- (KMMYCSkikoImageInfo *)makeN32PremulWidth:(int32_t)width height:(int32_t)height colorSpace:(KMMYCSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("makeN32Premul(width:height:colorSpace:)")));
- (KMMYCSkikoImageInfo *)makeS32Width:(int32_t)width height:(int32_t)height alphaType:(KMMYCSkikoColorAlphaType *)alphaType __attribute__((swift_name("makeS32(width:height:alphaType:)")));
- (KMMYCSkikoImageInfo *)makeUnknownWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeUnknown(width:height:)")));
@property (readonly) KMMYCSkikoImageInfo *DEFAULT __attribute__((swift_name("DEFAULT")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoData.Companion")))
@interface KMMYCSkikoDataCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoDataCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoData *)makeEmpty __attribute__((swift_name("makeEmpty()")));
- (KMMYCSkikoData *)makeFromBytesBytes:(KMMYCKotlinByteArray *)bytes offset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("makeFromBytes(bytes:offset:length:)")));
- (KMMYCSkikoData *)makeUninitializedLength:(int32_t)length __attribute__((swift_name("makeUninitialized(length:)")));
- (KMMYCSkikoData *)makeWithoutCopyMemoryAddr:(void * _Nullable)memoryAddr length:(int32_t)length underlyingMemoryOwner:(KMMYCSkikoManaged *)underlyingMemoryOwner __attribute__((swift_name("makeWithoutCopy(memoryAddr:length:underlyingMemoryOwner:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixmap.Companion")))
@interface KMMYCSkikoPixmapCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoPixmapCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoPixmap *)makeInfo:(KMMYCSkikoImageInfo *)info buffer:(KMMYCSkikoData *)buffer rowBytes:(int32_t)rowBytes __attribute__((swift_name("make(info:buffer:rowBytes:)")));
- (KMMYCSkikoPixmap *)makeInfo:(KMMYCSkikoImageInfo *)info addr:(void * _Nullable)addr rowBytes:(int32_t)rowBytes underlyingMemoryOwner:(KMMYCSkikoManaged * _Nullable)underlyingMemoryOwner __attribute__((swift_name("make(info:addr:rowBytes:underlyingMemoryOwner:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoBitmap.Companion")))
@interface KMMYCSkikoBitmapCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoBitmapCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoBitmap *)makeFromImageImage:(KMMYCSkikoImage *)image __attribute__((swift_name("makeFromImage(image:)")));
- (KMMYCSkikoBitmap *)makeFromImageImage:(KMMYCSkikoImage *)image context:(KMMYCSkikoDirectContext *)context __attribute__((swift_name("makeFromImage(image:context:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixelRef")))
@interface KMMYCSkikoPixelRef : KMMYCSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) KMMYCSkikoPixelRefCompanion *companion __attribute__((swift_name("companion")));
- (KMMYCSkikoPixelRef *)notifyPixelsChanged __attribute__((swift_name("notifyPixelsChanged()")));
- (KMMYCSkikoPixelRef *)setImmutable __attribute__((swift_name("setImmutable()")));
@property (readonly) int32_t generationId __attribute__((swift_name("generationId")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) BOOL isImmutable __attribute__((swift_name("isImmutable")));
@property (readonly) void * _Nullable rowBytes __attribute__((swift_name("rowBytes")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoDirectContext.Companion")))
@interface KMMYCSkikoDirectContextCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoDirectContextCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoDirectContext *)makeDirect3DAdapterPtr:(void * _Nullable)adapterPtr devicePtr:(void * _Nullable)devicePtr queuePtr:(void * _Nullable)queuePtr __attribute__((swift_name("makeDirect3D(adapterPtr:devicePtr:queuePtr:)")));
- (KMMYCSkikoDirectContext *)makeGL __attribute__((swift_name("makeGL()")));
- (KMMYCSkikoDirectContext *)makeMetalDevicePtr:(void * _Nullable)devicePtr queuePtr:(void * _Nullable)queuePtr __attribute__((swift_name("makeMetal(devicePtr:queuePtr:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoGLBackendState")))
@interface KMMYCSkikoGLBackendState : KMMYCKotlinEnum<KMMYCSkikoGLBackendState *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KMMYCSkikoGLBackendState *renderTarget __attribute__((swift_name("renderTarget")));
@property (class, readonly) KMMYCSkikoGLBackendState *textureBinding __attribute__((swift_name("textureBinding")));
@property (class, readonly) KMMYCSkikoGLBackendState *view __attribute__((swift_name("view")));
@property (class, readonly) KMMYCSkikoGLBackendState *blend __attribute__((swift_name("blend")));
@property (class, readonly) KMMYCSkikoGLBackendState *msaaEnable __attribute__((swift_name("msaaEnable")));
@property (class, readonly) KMMYCSkikoGLBackendState *vertex __attribute__((swift_name("vertex")));
@property (class, readonly) KMMYCSkikoGLBackendState *stencil __attribute__((swift_name("stencil")));
@property (class, readonly) KMMYCSkikoGLBackendState *pixelStore __attribute__((swift_name("pixelStore")));
@property (class, readonly) KMMYCSkikoGLBackendState *program __attribute__((swift_name("program")));
@property (class, readonly) KMMYCSkikoGLBackendState *fixedFunction __attribute__((swift_name("fixedFunction")));
@property (class, readonly) KMMYCSkikoGLBackendState *misc __attribute__((swift_name("misc")));
@property (class, readonly) KMMYCSkikoGLBackendState *pathRendering __attribute__((swift_name("pathRendering")));
+ (KMMYCKotlinArray<KMMYCSkikoGLBackendState *> *)values __attribute__((swift_name("values()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRuntimeEffect.Companion")))
@interface KMMYCSkikoRuntimeEffectCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoRuntimeEffectCompanion *shared __attribute__((swift_name("shared")));
- (KMMYCSkikoRuntimeEffect *)makeForColorFilterSksl:(NSString *)sksl __attribute__((swift_name("makeForColorFilter(sksl:)")));
- (KMMYCSkikoRuntimeEffect *)makeForShaderSksl:(NSString *)sksl __attribute__((swift_name("makeForShader(sksl:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix22.Companion")))
@interface KMMYCSkikoMatrix22Companion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoMatrix22Companion *shared __attribute__((swift_name("shared")));
@property (readonly) KMMYCSkikoMatrix22 *IDENTITY __attribute__((swift_name("IDENTITY")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixelRef.Companion")))
@interface KMMYCSkikoPixelRefCompanion : KMMYCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KMMYCSkikoPixelRefCompanion *shared __attribute__((swift_name("shared")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
