#
# Copyright 2009-2010 Igor Polevoy
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

#
# This file is a collection of unobtrusive JS that binds to link_to generated anchors typical for Ajax calls.
#
# author: Igor Polevoy
#
$ ->
  $().ready ->
    $("a[data-link]").bind "click", ->
      anchor = $(this)
      destination = anchor.attr("data-destination")
      formId = anchor.attr("data-form")
      href = anchor.attr("href")
      _method = anchor.attr("data-method")
      before = anchor.attr("data-before")
      after = anchor.attr("data-after")
      beforeArg = anchor.attr("data-before-arg")
      afterArg = anchor.attr("data-after-arg")
      error = anchor.attr("data-error")
      confirmMessage = anchor.attr("data-confirm")
      return false  unless confirm(confirmMessage)  if confirmMessage?

      #not Ajax
      return true  if not destination? and not before? and not after? and (not _method? or _method.toLowerCase() is "get")
      _method = "get"  unless _method?
      type = undefined
      if _method.toLowerCase() is "get"
        type = "get"
      else type = "post"  if _method.toLowerCase() is "post" or _method.toLowerCase() is "put" or _method.toLowerCase() is "delete"
      data = "_method=" + _method
      data += "&" + $("#" + formId).serialize()  if formId?
      eval_(before) beforeArg  if before?
      $.ajax
        url: href
        data: data
        type: type
        success: (data) ->
          eval_(after) afterArg, data  if after?
          $("#" + destination).html data  if destination?

        error: (xhr, status, errorThrown) ->
          eval_(error) xhr.status, xhr.responseText  if error?

      false