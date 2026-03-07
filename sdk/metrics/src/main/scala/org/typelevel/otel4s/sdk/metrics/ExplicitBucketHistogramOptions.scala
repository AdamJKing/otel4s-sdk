/*
 * Copyright 2024 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.typelevel.otel4s.sdk.metrics

import org.typelevel.otel4s.metrics.BucketBoundaries

/** Options for configuring explicit bucket histogram aggregation.
  */
sealed trait ExplicitBucketHistogramOptions {

  /** Explicit histogram bucket boundaries.
    */
  def bucketBoundaries: Option[BucketBoundaries]

  /** Whether min and max should be recorded for histogram points.
    */
  def recordMinMax: Boolean

  def withBucketBoundaries(value: BucketBoundaries): ExplicitBucketHistogramOptions

  def withRecordMinMax(value: Boolean): ExplicitBucketHistogramOptions
}

object ExplicitBucketHistogramOptions {

  private val Default =
    Impl(bucketBoundaries = None, recordMinMax = true)

  /** The default explicit bucket histogram options:
    *   - no explicit bucket boundaries
    *   - min and max values are recorded
    */
  def default: ExplicitBucketHistogramOptions =
    Default

  /** Creates explicit bucket histogram options.
    */
  def apply(
      bucketBoundaries: Option[BucketBoundaries],
      recordMinMax: Boolean
  ): ExplicitBucketHistogramOptions =
    Impl(bucketBoundaries = bucketBoundaries, recordMinMax = recordMinMax)

  private final case class Impl(
      bucketBoundaries: Option[BucketBoundaries],
      recordMinMax: Boolean
  ) extends ExplicitBucketHistogramOptions {
    def withBucketBoundaries(value: BucketBoundaries): ExplicitBucketHistogramOptions =
      copy(bucketBoundaries = Some(value))

    def withRecordMinMax(value: Boolean): ExplicitBucketHistogramOptions =
      copy(recordMinMax = value)
  }
}
