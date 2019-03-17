/*
 * Copyright (c) 2019 LinuxServer.io
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.linuxserver.fleet.web.routes;

import io.linuxserver.fleet.delegate.UserDelegate;
import io.linuxserver.fleet.exception.SaveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegisterInitialUserRoute implements Route {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterInitialUserRoute.class);

    private UserDelegate userDelegate;

    public RegisterInitialUserRoute(UserDelegate userDelegate) {
        this.userDelegate = userDelegate;
    }

    @Override
    public Object handle(Request request, Response response) {

        String username         = request.queryParams("username");
        String password         = request.queryParams("password");
        String verifyPassword   = request.queryParams("verify-password");

        if (!verifyPassword.equals(password)) {

            response.redirect("/admin/setup?passwordMismatch=true");
            return null;
        }

        try {

            userDelegate.createNewUser(username, password);
            response.redirect("/admin");

        } catch (SaveException e) {

            response.redirect("/admin/setup?createUserError=true");
            LOGGER.error("Unable to create new user", e);
        }

        return null;
    }
}
