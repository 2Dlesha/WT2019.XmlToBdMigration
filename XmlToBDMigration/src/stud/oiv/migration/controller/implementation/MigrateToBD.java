package stud.oiv.migration.controller.implementation;

import stud.oiv.migration.controller.Command;
import stud.oiv.migration.service.MigrationService;
import stud.oiv.migration.service.ServiceException;

public class MigrateToBD implements Command {
    /**
     * Execute data mmigration from xml format to db
     * @param params no params
     * @return 'success' if migratw is success error otherwise
     */
    @Override
    public String execute(String ... params) {
        String result = "";
        try {
            MigrationService.MigrateAll();
            result = "success";
        }catch (ServiceException e)
        {
            result = "error";
        }
        return "";
    }
}
