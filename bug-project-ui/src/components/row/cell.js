import React from "react";
import applyEllipsis from "../../utils";
import { Tooltip, OverlayTrigger } from "react-bootstrap";
export default function Cell(props) {
  return (
    <td>
      <OverlayTrigger
        placement="bottom"
        overlay={<Tooltip>{props.content}</Tooltip>}
      >
        <span>{applyEllipsis(props.content)}</span>
      </OverlayTrigger>
    </td>
  );
}
