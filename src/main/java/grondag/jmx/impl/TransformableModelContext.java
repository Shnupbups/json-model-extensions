/*******************************************************************************
 * Copyright 2019 grondag
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package grondag.jmx.impl;

import net.fabricmc.fabric.api.renderer.v1.render.RenderContext.QuadTransform;

/**
 * Functions used by transformable models to transform their internal state.
 */
public interface TransformableModelContext {
    /** Used to transform particle sprite or other sprites not part of a mesh */
    SpriteMap spriteTransform();
    
    /** Apply to all meshes in the model */
    QuadTransform quadTransform();
    
    /** 
     * For multi-part models, use to map new model states back to original state
     * so that predicate structure can be reused.
     * */
    InverseStateMap inverseStateMap();
}
