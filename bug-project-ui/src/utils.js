export default function applyEllipsis(data) {
  return data.length > 20 ? data.substr(0, 20) + "..." : data;
}
