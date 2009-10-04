/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.webbeans.resolution;

import java.util.Collections;
import java.util.Set;

import org.jboss.webbeans.BeanManagerImpl;
import org.jboss.webbeans.bean.DisposalMethod;
import org.jboss.webbeans.util.Beans;
import org.jboss.webbeans.util.Reflections;

/**
 * @author pmuir
 *
 */
public class TypeSafeDisposerResolver extends TypeSafeResolver<DisposalMethod<?, ?>>
{

   private final BeanManagerImpl manager;

   public TypeSafeDisposerResolver(BeanManagerImpl manager, Iterable<DisposalMethod<?, ?>> disposers)
   {
      super(disposers);
      this.manager = manager;
   }

   @Override
   protected boolean matches(Resolvable resolvable, DisposalMethod<?, ?> disposer)
   {
         return resolvable.getDeclaringBean().equals(disposer.getDeclaringBean()) && Reflections.isAssignableFrom(disposer.getType(), resolvable.getTypeClosure()) && Beans.containsAllBindings(disposer.getQualifiers(), resolvable.getQualifiers(), manager);
   }
   
   /**
    * @return the manager
    */
   public BeanManagerImpl getManager()
   {
      return manager;
   }

   @Override
   protected Set<DisposalMethod<?, ?>> filterResult(Set<DisposalMethod<?, ?>> matched)
   {
      return matched;
   }

   @Override
   protected Iterable<ResolvableTransformer> getTransformers()
   {
      return Collections.emptySet();
   }

   @Override
   protected Set<DisposalMethod<?, ?>> sortResult(Set<DisposalMethod<?, ?>> matched)
   {
      return matched;
   }

}
