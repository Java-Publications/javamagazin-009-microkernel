package org.rapidpm.microkernel.legacy;

import org.rapidpm.microkernel.api.Service;

/**
 *
 */
public class ServiceLegacyImpl implements Service {
  @Override
  public int add(int a , int b) {
    return a + b;
  }
}
