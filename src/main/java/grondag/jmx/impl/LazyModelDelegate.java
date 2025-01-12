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

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import grondag.frex.api.model.LazyForwardingBakedModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

public class LazyModelDelegate extends LazyForwardingBakedModel implements UnbakedModel {

    private final ModelTransformer transformer;
    private final ModelIdentifier templateId;
    
    public LazyModelDelegate(ModelIdentifier templateId, ModelTransformer transformer) {
        this.templateId = templateId;
        this.transformer = transformer;
    }
    
    @Override
    public Collection<Identifier> getModelDependencies() {
        return Collections.emptyList();
    }

    @Override
    public Collection<Identifier> getTextureDependencies(Function<Identifier, UnbakedModel> modelFunc, Set<String> errors) {
        return transformer.textures();
    }

    @Override
    public BakedModel bake(ModelLoader modelLoader, Function<Identifier, Sprite> spriteFunc, ModelBakeSettings bakeProps) {
        return this;
    }

    @Override
    protected BakedModel createWrapped() {
        return transformer.transform(MinecraftClient.getInstance().getBakedModelManager().getModel(templateId));
    }
}
