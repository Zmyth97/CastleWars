package com.desitum.castleWars.libraries.interpolation;

/**
 * Created by dvan6234 on 2/24/2015.
 */
/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import com.badlogic.gdx.utils.Pool;

/**
 * An interpolator where the rate of change starts out slowly and then accelerates over time.
 *
 * @author Moritz Post <moritzpost@gmail.com>
 */
public class AccelerateInterpolator implements Interpolator {

    private static final float DEFAULT_FACTOR = 1.0f;

    private static final Pool<AccelerateInterpolator> pool = new Pool<AccelerateInterpolator>(4, 100) {
        @Override
        protected AccelerateInterpolator newObject() {
            return new AccelerateInterpolator();
        }
    };

    private float factor;

    private double doubledFactor;

    AccelerateInterpolator() {
        // hide constructor
    }

    /**
     * Gets a new {@link com.desitum.shveetlife.libraries.interpolation.AccelerateInterpolator} from a maintained pool of {@link Interpolator}s.
     *
     * @param factor the factor controlling the rate of change
     * @return the obtained {@link com.desitum.shveetlife.libraries.interpolation.AccelerateInterpolator}
     */
    public static AccelerateInterpolator $(float factor) {
        AccelerateInterpolator inter = pool.obtain();
        inter.factor = factor;
        inter.doubledFactor = factor * 2;
        return inter;
    }

    /**
     * Gets a new {@link com.desitum.shveetlife.libraries.interpolation.AccelerateInterpolator} from a maintained pool of {@link Interpolator}s.
     * <p/>
     * The initial factor is set to <code>{@value com.desitum.shveetlife.libraries.interpolation.AccelerateInterpolator#DEFAULT_FACTOR}</code>.
     *
     * @return the obtained {@link com.desitum.shveetlife.libraries.interpolation.AccelerateInterpolator}
     */
    public static AccelerateInterpolator $() {
        return $(DEFAULT_FACTOR);
    }

    @Override
    public void finished() {
        pool.free(this);
    }

    public float getInterpolation(float input) {
        if (factor == 1.0f) {
            return input * input;
        } else {
            return (float) Math.pow(input, doubledFactor);
        }
    }

    @Override
    public Interpolator copy() {
        return $(factor);
    }
}
