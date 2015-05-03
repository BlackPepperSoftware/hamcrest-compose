/*
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
package org.hobsoft.hamcrest.compose;

import java.util.function.Function;

import org.hamcrest.Matcher;

import static java.util.Collections.singletonList;

/**
 * Factory for Hamcrest Compose matchers.
 */
public final class ComposeMatchers
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	private ComposeMatchers()
	{
		throw new AssertionError();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * Returns a matcher that logically ANDs the specified matcher with any number of further matchers.
	 * <p>
	 * For example:
	 * <pre>
	 * assertThat("xyz", compose(startsWith("x")).and(containsString("y")).and(endsWith("z")));
	 * </pre>
	 * See {@code ConjunctionMatcher} as to how this matcher differs from {@code allOf} and {@code both}.
	 * 
	 * @param matcher
	 *            the first matcher to compose
	 * @return a matcher that can compose itself with further matchers
	 * @see ConjunctionMatcher
	 */
	public static <T> ConjunctionMatcher<T> compose(Matcher<T> matcher)
	{
		return new ConjunctionMatcher<>(singletonList(matcher));
	}
	
	public static <T, U> Matcher<T> hasFeature(String featureName, Function<T, U> featureFunction,
		Matcher<? super U> featureMatcher)
	{
		return new HasFeatureMatcher<>(featureName, featureName, featureFunction, featureMatcher);
	}
}