package com.masterclass.employee.directory.display;

import com.masterclass.employee.directory.model.Employee;
import java.util.List;
import java.util.function.Consumer;

public final class DisplaySupplier {

  public static Consumer<List<Employee>> getDefaultDisplayForListingEmployees() {
    return new DefaultDisplayForListingEmployees();
  }

  public static Consumer<List<Employee>> getDefaultDisplayForNewlyAddedEmployee() {
    return new DefaultDisplayForNewlyAddedEmployee();
  }
}
