package org.jboss.webbeans.bootstrap;

import java.util.HashSet;
import java.util.Set;

import org.jboss.webbeans.BeanManagerImpl;
import org.jboss.webbeans.bean.builtin.ExtensionBean;
import org.jboss.webbeans.ejb.EjbDescriptors;

public class ExtensionBeanDeployerEnvironment extends BeanDeployerEnvironment
{
   
   private final Set<ExtensionBean> extensionBeans;
   

   public ExtensionBeanDeployerEnvironment(EjbDescriptors ejbDescriptors, BeanManagerImpl manager)
   {
      super(ejbDescriptors, manager);
      this.extensionBeans = new HashSet<ExtensionBean>();
   }
   
   @Override
   public Set<ExtensionBean> getBeans()
   {
      return extensionBeans;
   }
   
   @Override
   public void addBean(ExtensionBean bean)
   {
      extensionBeans.add(bean);
   }

}
